package com.vsu.webmarket.web.controllers.rest.restmodel;

import com.vsu.webmarket.model.Article;
import com.vsu.webmarket.model.User;

import java.io.Serializable;
import java.util.Calendar;

public class HistoryResponseData implements Serializable {
    private long id;
    private User user;
    private Article article;
    private Calendar lastViewed;

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
