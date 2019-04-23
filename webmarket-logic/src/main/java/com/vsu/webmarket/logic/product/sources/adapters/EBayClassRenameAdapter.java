package com.vsu.webmarket.logic.product.sources.adapters;

import com.vsu.webmarket.logic.product.ProductSource;
import com.vsu.webmarket.logic.product.productmodel.ProductInDetail;
import com.vsu.webmarket.logic.product.productmodel.ProductInList;
import com.vsu.webmarket.logic.product.sources.EBayClassRenameMe;

import java.util.List;

public class EBayClassRenameAdapter implements ProductSource {
    private EBayClassRenameMe origin;

    public EBayClassRenameAdapter(EBayClassRenameMe origin) {
        this.origin = origin;
    }

    @Override
    public List<ProductInList> getSearchResult(String searchPhrase) {
        return null;
    }

    @Override
    public ProductInDetail getDetailedArticle(String productId) {
        return null;
    }
}
