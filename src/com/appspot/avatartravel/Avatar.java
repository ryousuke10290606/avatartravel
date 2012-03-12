package com.appspot.avatartravel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public abstract class Avatar {
    private static final ArrayList<Avatar> avatars;
    /* アバターごとのクラスの管理 */
    static {
        avatars = new ArrayList<Avatar>();
        /* 以下三行が初めにコンパイルエラーとなる。 
           個別のAvatarクラスを作成するとコンパイルエラーはなくなる */
        avatars.add(new EriAvatar());
        avatars.add(new SounAvatar());
        avatars.add(new O2Avatar());
    }

    /* アバターの一覧を取得する */
    @SuppressWarnings("unchecked")
    public static List<Avatar> getAvatars() {
        return (List<Avatar>) avatars.clone();
    }/* IDを指定してアバタークラスを取得する */

    public static Avatar getAvatar(String avatarId) {
        for (Avatar avatar : avatars) {
            if (avatarId.equals(avatar.getId())) {
                return avatar;
            }
        }
        return null;
    }

    /* 継承クラスが実装するべきメソッドの定義 */
    public abstract String getId(); /* アバターのId */
    public abstract String getName(); /* アバターの名前 */
    public abstract String getImageName(); /* アバターの画像ファイル名 */
    public abstract String getMessage(); /* 出発時のメッセージ */
    /* ブログに記事を登録する処理 */
    public abstract void createArticle(Blog blog);

    /* 以下、継承クラスから利用できるヘルパーメソッド */

    /* キーワードに関係する写真を選択してBlogエンティティに記事を登録する */
    protected void addArticle(Blog blog, String text, String keywords,
            int nextPosthour) {
        FlickrHelper helper = FlickrHelper.newHelper(keywords);
        Date postDate = new Date();
        String pageUrl = "";
        String imageUrl = "";
        /* Flickr APIに接続できたときだけ、写真の情報を登録する */
        if (helper != null) {
            pageUrl = helper.getPageUrl();
            imageUrl = helper.getImageUrl();
        }
        /* 記事をブログに登録する */
        Article article = new Article(postDate, text, pageUrl, imageUrl);
        blog.getArticles().add(article);
        /* 次の記事の投稿予定日時を登録する */
        if (0 <= nextPosthour) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(postDate);
            calendar.add(Calendar.HOUR, nextPosthour);
            blog.setNextPostDate(calendar.getTime());
        } else {
            abort(blog);
        }
    }

    /* 旅行を終了する（未来日を入れる） */
    protected void abort(Blog blog) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 9999);
        blog.setNextPostDate(calendar.getTime());
    }
}