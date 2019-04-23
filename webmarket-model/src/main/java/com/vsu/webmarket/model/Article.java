package com.vsu.webmarket.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "articles", schema = "public")
public class Article {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "external_id")
    private String externalId;

    @Column(name = "product_web_url")
    private String productWebUrl;

    @Column(name = "title")
    private String title;

    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY)
    private List<Favourite> favourites;

    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY)
    private List<History> histories;

    public Article() {
        this.favourites = new ArrayList<>();
        this.histories = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getProductWebUrl() {
        return productWebUrl;
    }

    public void setProductWebUrl(String productWebUrl) {
        this.productWebUrl = productWebUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<Favourite> getFavourites() {
        return favourites;
    }

    public void setFavourites(List<Favourite> favourites) {
        this.favourites = favourites;
    }

    public List<History> getHistories() {
        return histories;
    }

    public void setHistories(List<History> histories) {
        this.histories = histories;
    }
}
