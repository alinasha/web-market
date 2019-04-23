package com.vsu.webmarket.model;

import javax.persistence.*;

@Entity
@Table(name = "history")
public class History {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "user_id", nullable = false)
    private long userId;

    @Column(name = "article_id", nullable = false)
    private long articleId;

    @Column(name = "last_viewed", nullable = false)
    private long lastViewed;

    public History() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

    public long getLastViewed() {
        return lastViewed;
    }

    public void setLastViewed(long lastViewed) {
        this.lastViewed = lastViewed;
    }
}
