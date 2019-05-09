package com.vsu.webmarket.logic.product;

import com.sun.istack.Nullable;
import com.vsu.webmarket.logic.product.productmodel.ProductInDetail;
import com.vsu.webmarket.logic.product.productmodel.ProductInList;
import com.vsu.webmarket.logic.product.sources.adapters.EBayClassRenameAdapter;

import java.util.List;

public interface ProductSource {
    List<ProductInList> getSearchResult(String searchPhrase, int pageNumber);

    List<ProductInList> getSearchResult(String searchPhrase, int pageNumber, EBayClassRenameAdapter.SortType sortType);

    List<ProductInList> getSearchResult(String searchPhrase, int pageNumber, EBayClassRenameAdapter.SortType sortType, double minPriceRange, double maxPriceRange);

    ProductInDetail getDetailedArticle(String productId);
}
