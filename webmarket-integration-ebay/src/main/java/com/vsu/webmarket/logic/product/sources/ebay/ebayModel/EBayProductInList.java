package com.vsu.webmarket.logic.product.sources.ebay.ebayModel;

public class EBayProductInList {
    private String productId;
    private String title;
    private String imageUrl;
    private String price;

    public EBayProductInList() {
    }

    public EBayProductInList(String productId, String title, String imageUrl, String price) {
        this.productId = productId;
        this.title = title;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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
}
