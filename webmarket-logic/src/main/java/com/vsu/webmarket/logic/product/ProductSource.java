package com.vsu.webmarket.logic.product;

import com.vsu.webmarket.logic.product.productmodel.ProductInDetail;
import com.vsu.webmarket.logic.product.productmodel.ProductInList;

import java.util.List;

public interface ProductSource {

    enum SortType {
        HIGH_PRICE_FIRST,
        LOWER_PRICE_FIRST
    }

    List<ProductInList> getSearchResult(String searchPhrase, int pageNumber);

    List<ProductInList> getSearchResult(String searchPhrase, int pageNumber, SortType sortType);

    List<ProductInList> getSearchResult(String searchPhrase, int pageNumber, SortType sortType,
                                        double minPriceRange, double maxPriceRange);

    ProductInDetail getDetailedArticle(String productId);
}
