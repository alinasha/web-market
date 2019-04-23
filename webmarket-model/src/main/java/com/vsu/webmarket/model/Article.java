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

    @Column(name = "product_exists", nullable = false)
    private boolean productExists;

    @Column(name = "brand")
    private String brand;

    @Column(name = "description")
    private String description;

    @Column(name = "external_id")
    private String extentalId;

    @Column(name = "product_web_url")
    private String productWebUrl;

    @Column(name = "title")
    private String title;

    @Column(name = "version")
    private String version;

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

    public boolean isProductExists() {
        return productExists;
    }

    public void setProductExists(boolean productExists) {
        this.productExists = productExists;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExtentalId() {
        return extentalId;
    }

    public void setExtentalId(String extentalId) {
        this.extentalId = extentalId;
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
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
