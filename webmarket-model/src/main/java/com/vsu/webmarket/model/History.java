package com.vsu.webmarket.model;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "history", schema = "public")
public class History {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;

    @Column(name = "last_viewed", nullable = false)
    private Calendar lastViewed;

    public History() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Calendar getLastViewed() {
        return lastViewed;
    }

    public void setLastViewed(Calendar lastViewed) {
        this.lastViewed = lastViewed;
    }
}
