package com.vsu.webmarket.logic.product.sources.adapters;

import com.vsu.webmarket.logic.product.ProductSource;
import com.vsu.webmarket.logic.product.sources.EBayClassRenameMe;
import com.vsu.webmarket.model.Article;

import java.util.List;

public class EBayClassRenameAdapter implements ProductSource {
    private EBayClassRenameMe origin;

    public EBayClassRenameAdapter(EBayClassRenameMe origin) {
        this.origin = origin;
    }

    @Override
    public List<Article> getSearchResult(String searchPhrase) {
        return null;
    }

    @Override
    public Article getDetailedArticle(String productId) {
        return null;
    }
}
