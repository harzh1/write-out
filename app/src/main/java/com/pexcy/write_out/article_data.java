package com.pexcy.write_out;

public class article_data {

    String TopicName;
    String Article;

    public article_data(String topicName, String article) {
        TopicName = topicName;
        Article = article;
    }

    public String getTopicName() {
        return TopicName;
    }

    public String getArticle() {
        return Article;
    }
}
