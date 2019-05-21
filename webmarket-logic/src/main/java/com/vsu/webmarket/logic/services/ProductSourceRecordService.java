package com.vsu.webmarket.logic.services;

import com.vsu.webmarket.ApplicationContextProvider;
import com.vsu.webmarket.data.repositories.ArticleRepository;
import com.vsu.webmarket.data.repositories.HistoryRepository;
import com.vsu.webmarket.data.services.ArticleService;
import com.vsu.webmarket.data.services.HistoryService;
import com.vsu.webmarket.logic.product.ProductSource;
import com.vsu.webmarket.logic.product.productmodel.ProductInDetail;
import com.vsu.webmarket.logic.product.productmodel.ProductInList;
import com.vsu.webmarket.model.Article;
import com.vsu.webmarket.model.History;
import com.vsu.webmarket.model.User;

import java.util.*;

public class ProductSourceRecordService implements ProductSource {
    private ProductSource productSource;
    private User user;

    public ProductSourceRecordService(ProductSource productSource, User user) {
        this.productSource = productSource;
        this.user = user;
    }

    @Override
    public List<ProductInList> getSearchResult(String searchPhrase, int pageNumber) {
        return productSource.getSearchResult(searchPhrase, pageNumber);
    }

    @Override
    public List<ProductInList> getSearchResult(String searchPhrase, int pageNumber, SortType sortType) {
        return productSource.getSearchResult(searchPhrase, pageNumber, sortType);
    }

    @Override
    public List<ProductInList> getSearchResult(String searchPhrase, int pageNumber, SortType sortType, double minPriceRange, double maxPriceRange) {
        return productSource.getSearchResult(searchPhrase, pageNumber, sortType, minPriceRange, maxPriceRange);
    }

    @Override
    public ProductInDetail getDetailedArticle(String productId) {
        ProductInDetail productInDetail = productSource.getDetailedArticle(productId);
        addToHistory(productInDetail);
        return productInDetail;
    }

    private void addToHistory(ProductInDetail productInDetail) {
        //-----------------------------------------------------------------------------------------------
        //Article
        //-----------------------------------------------------------------------------------------------
        //Новый Article
        Article article = new Article();
        long productId = 0;
        try {
            productId = Long.parseLong(productInDetail.getProductListInfo().getProductId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (productInDetail != null && productInDetail.getProductListInfo() != null) {
            article.setId(productId);
            article.setTitle(productInDetail.getProductListInfo().getTitle());
            article.setImageUrl(productInDetail.getProductListInfo().getImageUrl());
            article.setPrice(productInDetail.getProductListInfo().getPrice());
            article.setProductUrl(productInDetail.getProductUrl());
        }

        //добавляем в БД новый article если его небыло либо обновляем если он есть
        ArticleRepository articleRepository = ApplicationContextProvider.getApplicationContext().getBean(ArticleService.class).getRepository();
        articleRepository.save(article);

        //-----------------------------------------------------------------------------------------------
        //History
        //-----------------------------------------------------------------------------------------------
        //Новый History
        History history = new History();
        history.setUser(user);
        history.setArticle(article);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        calendar.setTime(new Date());
        history.setLastViewed(calendar);

        HistoryRepository historyRepository = ApplicationContextProvider.getApplicationContext().getBean(HistoryService.class).getRepository();
        if (user.getHistories().size() > 0) {
            History lastHistory = user.getHistories().get(user.getHistories().size()-1);
            if (lastHistory.getArticle() != null && article.getId() == lastHistory.getArticle().getId()) {
                //если последняя запись из истории пользователя article_id совпадает с article_id просмотренного товара то удаляем эту запись из БД
                historyRepository.delete(lastHistory);
            }
        }

        //добавление к пользователю новой записи о просмотре товара
        historyRepository.save(history);
    }
}
