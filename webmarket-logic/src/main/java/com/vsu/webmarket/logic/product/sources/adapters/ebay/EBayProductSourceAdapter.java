package com.vsu.webmarket.logic.product.sources.adapters.ebay;

import com.vsu.webmarket.logic.product.ProductSource;
import com.vsu.webmarket.logic.product.productmodel.ProductInDetail;
import com.vsu.webmarket.logic.product.productmodel.ProductInList;
import com.vsu.webmarket.logic.product.sources.ebay.EBayClient;
import com.vsu.webmarket.logic.product.sources.ebay.ebayModel.EBayProductInList;

import java.util.ArrayList;
import java.util.List;

public class EBayProductSourceAdapter implements ProductSource {
    private EBayClient origin;

    public EBayProductSourceAdapter(EBayClient origin) {
        this.origin = origin;
    }

    @Override
    public List<ProductInList> getSearchResult(String searchPhrase, int pageNumber) {
        List<EBayProductInList> searchResult = origin.getSearchResult(searchPhrase, pageNumber);
        List<ProductInList> adaptedResult = new ArrayList<>();
        searchResult.forEach(x -> adaptedResult.add(new ProductInListEbayAdapter(x)));
        return adaptedResult;
    }

    @Override
    public List<ProductInList> getSearchResult(String searchPhrase, int pageNumber, SortType sortType) {
        EBayClient.EBaySortType eBaySortType = sortType == null ? null : getEBaySortType(sortType);
        List<EBayProductInList> searchResult = origin.getSearchResult(searchPhrase, pageNumber, eBaySortType);
        List<ProductInList> adaptedResult = new ArrayList<>();
        searchResult.forEach(x -> adaptedResult.add(new ProductInListEbayAdapter(x)));
        return adaptedResult;
    }

    @Override
    public List<ProductInList> getSearchResult(String searchPhrase, int pageNumber,
                                               SortType sortType, double minPriceRange, double maxPriceRange) {
        EBayClient.EBaySortType eBaySortType = sortType == null ? null : getEBaySortType(sortType);
        List<EBayProductInList> searchResult = origin.getSearchResult(searchPhrase, pageNumber, eBaySortType,
                minPriceRange, maxPriceRange);
        List<ProductInList> adaptedResult = new ArrayList<>();
        searchResult.forEach(x -> adaptedResult.add(new ProductInListEbayAdapter(x)));
        return adaptedResult;
    }

    @Override
    public ProductInDetail getDetailedArticle(String productId) {
        return new ProductInDetailEbayAdapter(origin.getDetailedArticle(productId));
    }

    private EBayClient.EBaySortType getEBaySortType(SortType sortType) {
        EBayClient.EBaySortType eBaySortType;
        switch (sortType) {
            case HIGH_PRICE_FIRST:
                eBaySortType = EBayClient.EBaySortType.HIGH_PRICE_FIRST;
                break;

            case LOWER_PRICE_FIRST:

            default:
                eBaySortType = EBayClient.EBaySortType.LOWER_PRICE_FIRST;
                break;
        }
        return eBaySortType;
    }
}
