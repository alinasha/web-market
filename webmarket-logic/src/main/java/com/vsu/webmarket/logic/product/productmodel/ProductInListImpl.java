package com.vsu.webmarket.logic.product.productmodel;

public class ProductInListImpl implements ProductInList {
    private String productId;
    private String title;
    private String imageUrl;
    private String price;

    public ProductInListImpl(String productId, String title, String imageUrl, String price) {
        this.productId = productId;
        this.title = title;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    @Override
    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getPrice() {
        return price;
    }

    @Override
    public String getProductId() {
        return productId;
    }
}
