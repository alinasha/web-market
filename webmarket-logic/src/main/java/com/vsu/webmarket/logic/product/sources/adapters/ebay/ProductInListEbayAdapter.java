package com.vsu.webmarket.logic.product.sources.adapters.ebay;

import com.vsu.webmarket.logic.product.productmodel.ProductInList;
import com.vsu.webmarket.logic.product.sources.ebay.ebayModel.EBayProductInList;

public class ProductInListEbayAdapter implements ProductInList {
    private EBayProductInList origin;

    public ProductInListEbayAdapter(EBayProductInList origin) {
        this.origin = origin;
    }

    @Override
    public String getImageUrl() {
        return origin.getImageUrl();
    }

    @Override
    public String getTitle() {
        return origin.getTitle();
    }

    @Override
    public String getPrice() {
        return origin.getPrice();
    }

    @Override
    public String getProductId() {
        return origin.getProductId();
    }
}
