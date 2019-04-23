package com.vsu.webmarket.logic.product.productmodel;

import java.util.List;

public interface ProductInDetail {
    ProductInList getProductListInfo();
    String getDescription();
    List<String> getImageUrls();
    List<String> getParameters();
}
