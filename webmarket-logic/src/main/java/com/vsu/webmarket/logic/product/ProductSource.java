package com.vsu.webmarket.logic.product;

import com.vsu.webmarket.model.Article;

import java.util.List;

public interface ProductSource {
    List<Article> getSearchResult(String searchPhrase);

    Article getDetailedArticle(String productId);
}
