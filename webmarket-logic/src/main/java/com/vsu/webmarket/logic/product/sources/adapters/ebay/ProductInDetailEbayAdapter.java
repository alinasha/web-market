package com.vsu.webmarket.logic.product.sources.adapters.ebay;

import com.vsu.webmarket.logic.product.productmodel.ProductInDetail;
import com.vsu.webmarket.logic.product.productmodel.ProductInList;
import com.vsu.webmarket.logic.product.sources.ebay.ebayModel.EBayProductInDetail;

import java.util.List;

public class ProductInDetailEbayAdapter implements ProductInDetail {
    private EBayProductInDetail origin;

    public ProductInDetailEbayAdapter(EBayProductInDetail origin) {
        this.origin = origin;
    }

    @Override
    public String getWebUrl() {
        return origin.getWebUrl();
    }

    @Override
    public ProductInList getProductListInfo() {
        return new ProductInListEbayAdapter(origin.getProductInList());
    }

    @Override
    public String getDescription() {
        return origin.getDescription();
    }

    @Override
    public List<String> getImageUrls() {
        return origin.getImageUrls();
    }

    @Override
    public List<String> getParameters() {
        return origin.getParameters();
    }

    @Override
    public String toString() {
        return origin.toString();
    }
}
