package com.appspot.avatartravel;

public class O2Avatar extends Avatar {
    /* アバターIDを返す */
    @Override public String getId() { return "o2"; }
    @Override public String getName() { return "Ｏ２"; }
    @Override public String getImageName() { return "avatar_o2.jpg"; }
    @Override public String getMessage() {
        return "ホイジャア、行ッテクルガネ。気ガ向イタラblogヲ書クンデ、読ヨンデチョ。";
    }

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
        String text = "夢ニマデミタ" + blog.getDestination()
                + "ニ着イチマッタダガネ。ヨーケ休ンデ明日カラウロウロシヨマイ。";
        String keywords = blog.getDestination();
        int nextPostHour = 12;
        addArticle(blog, text, keywords, nextPostHour);
    }

    private void addArticle01(Blog blog) {
        String text = "見タコトモナイ鳥ガオッタ。ヤッパ" + blog.getDestination() + "ハ、チャウナア";
        String keywords = blog.getDestination() + " bird";
        int nextPostHour = 18;
        addArticle(blog, text, keywords, nextPostHour);
    }

    private void addArticle02(Blog blog) {
        String text = "オ菓子ヲ買ッタ。ろぼっとダケド。";
        String keywords = blog.getDestination() + " goody";
        int nextPostHour = 18;
        addArticle(blog, text, keywords, nextPostHour);
    }

    private void addArticle03(Blog blog) {
        String text = "ソロソロばってりガ無クナリソウダ。早ヨ帰ッテ充電シヨマイ。";
        String keywords = blog.getDestination() + " road";
        int nextPostHour = -1;
        addArticle(blog, text, keywords, nextPostHour);
    }
}
