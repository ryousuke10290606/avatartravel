package com.appspot.avatartravel;

public class SounAvatar extends Avatar {
    /* アバターIDを返す */
    @Override public String getId() { return "soun"; }
    @Override public String getName() { return "早雲"; }
    @Override public String getImageName() { return "avatar_soun.jpg"; }
    @Override public String getMessage() {
        return "じゃあ、いってくる。何が待っているか楽しみだ。見て聞いて感じたことをblogに書くので、読んでくれよな。";
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
        String text = blog.getDestination() + "に到着！　今日はとりあえず寝る。明日からは"
                + blog.getDestination() + "巡り、がんばるぞ！";
        String keywords = blog.getDestination();
        int nextPostHour = 12;
        addArticle(blog, text, keywords, nextPostHour);
    }

    private void addArticle01(Blog blog) {
        String text = blog.getDestination() + "はまるで、時の流れが止まったかのようだ。なにもかもが美しい。";
        String keywords = blog.getDestination() + " time";
        int nextPostHour = 18;
        addArticle(blog, text, keywords, nextPostHour);
    }

    private void addArticle02(Blog blog) {
        String text = "旅も明日で終わりだ。みんなにいろいろ土産を買おうと思って探したら、これを見つけた。どうだろう？";
        String keywords = blog.getDestination() + " souvenir";
        int nextPostHour = 18;
        addArticle(blog, text, keywords, nextPostHour);
    }

    private void addArticle03(Blog blog) {
        String text = "今回の旅は、僕には忘れられない体験となった。いつかまた" + blog.getDestination()
                + "を訪れたい。";
        String keywords = blog.getDestination() + " scene";
        int nextPostHour = -1;
        addArticle(blog, text, keywords, nextPostHour);
    }
}
