package com.vsu.webmarket.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Информация о продукте магазина.
 *
 * Заголовок, ссылка на изображение, цена и ссылка на товар
 */
@Entity
@Table(name = "articles", schema = "public")
public class Article {

    /**
     * Идентификатор продукта.
     *
     * Уникальный номер по которому можно различить от других продуктов
     * Может быть сгенерирован на основе ссылки на товар если магазин не использует id
     */
    @Id
    private long id;

    /**
     * Заголовок товара.
     */
    @Column(name = "title")
    private String title;

    /**
     * Полная ссылка на изображение товара.
     */
    @Column(name = "image_url")
    private String imageUrl;

    /**
     * Цена товара.
     *
     * Может быть указана с буквенным кодом валюты перед ценой
     */
    @Column(name = "price")
    private String price;

    /**
     * Полная ссылка на товар магазина.
     */
    @Column(name = "product_url")
    private String productUrl;

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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
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

    /**
     * Может быть использован для генерирования productId.
     *
     * Если например магазин не использует идентификатор
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 37 * result + (title == null ? 0 : title.hashCode());
        result = 37 * result + (imageUrl == null ? 0 : imageUrl.hashCode());
        result = 37 * result + (price == null ? 0 : price.hashCode());
        result = 37 * result + (productUrl == null ? 0 : productUrl.hashCode());
        return result;
    }
}
