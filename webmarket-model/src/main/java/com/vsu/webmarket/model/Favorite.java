package com.vsu.webmarket.model;

import javax.persistence.*;

@Entity
@Table(name = "favorites")
public class Favorite {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "user_id", nullable = false)
    private long userId;

    @Column(name = "article_id", nullable = false)
    private long articleId;

    public Favorite() {
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
}
