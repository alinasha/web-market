package com.vsu.webmarket.logic.product;

import com.vsu.webmarket.logic.product.productmodel.ProductInDetail;
import com.vsu.webmarket.logic.product.productmodel.ProductInList;

import java.util.List;

public interface ProductSource {
    List<ProductInList> getSearchResult(String searchPhrase);

    ProductInDetail getDetailedArticle(String productId);
}
