package com.appspot.avatartravel;

public class EriAvatar extends Avatar {
    @Override public String getId() { return "eri"; }
    @Override public String getName() { return "えり"; }
    @Override public String getImageName() { return "avatar_eri.jpg"; }
    @Override public String getMessage() {
        return "それじゃあ、いってきます。感動したことがあったらblogに書くので、見てね。";
    }

    /* 間隔をあけて全部で四回、記事を投稿する */
    @Override
    public void createArticle(Blog blog) {
        switch (blog.getArticles().size()) {
        case 0: addArticle00(blog); break;
        case 1: addArticle01(blog); break;
        case 2: addArticle02(blog); break;
        case 3: addArticle03(blog); break;
        default: abort(blog); break;
        }
    }

    private void addArticle00(Blog blog) {
        String text = "思ってたより遠くて、ちょっと疲れた。明日からは、" + blog.getDestination()
                + "をいろいろ見て回ろう。今日はお休み。";
        String keywords = blog.getDestination();
        int nextPostHour = 12;
        addArticle(blog, text, keywords, nextPostHour);
    }

    private void addArticle01(Blog blog) {
        String text = "ここに来る前からグルメ雑誌で調べていた料理を食べたよ。もー、ほんとおいしかった。";
        String keywords = blog.getDestination() + " meal";
        int nextPostHour = 18;
        addArticle(blog, text, keywords, nextPostHour);
    }

    private void addArticle02(Blog blog) {
        String text = blog.getDestination()
                + "の名所にやってきた。写真と本物では、大違いだなぁ。来てよかった。";
        String keywords = blog.getDestination() + " famous";
        int nextPostHour = 18;
        addArticle(blog, text, keywords, nextPostHour);
    }

    private void addArticle03(Blog blog) {
        String text = blog.getDestination() + "の旅、本当に楽しかった。来年もまた来ようっと。";
        String keywords = blog.getDestination() + " evening";
        int nextPostHour = -1;
        addArticle(blog, text, keywords, nextPostHour);
    }
}