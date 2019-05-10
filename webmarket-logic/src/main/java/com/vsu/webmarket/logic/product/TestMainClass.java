package com.vsu.webmarket.logic.product;

import com.vsu.webmarket.logic.product.productmodel.ProductInDetail;
import com.vsu.webmarket.logic.product.productmodel.ProductInList;
import com.vsu.webmarket.logic.product.sources.adapters.ebay.EBayProductSourceAdapter;
import com.vsu.webmarket.logic.product.sources.ebay.EBayClient;

import java.util.List;

public class TestMainClass {
    public static void main(String[] args) {
        //тест работоспособности запросов
        test1();
    }

    private static void test1() {
        EBayClient origin = new EBayClient();
        EBayProductSourceAdapter adapter = new EBayProductSourceAdapter(origin);

        //список товаров
        String searchPhrase = "iphone";
        //List<ProductInList> list = adapter.getSearchResult(searchPhrase, 1);
        //List<ProductInList> list = adapter.getSearchResult(searchPhrase, 1, EBayProductSourceAdapter.SortType.HIGH_PRICE_FIRST);
        //List<ProductInList> list = adapter.getSearchResult(searchPhrase, 1, EBayProductSourceAdapter.SortType.LOWER_PRICE_FIRST, 1, 2);
        List<ProductInList> list = adapter.getSearchResult(searchPhrase, 1, null, 10, 20);
        System.out.println("ПОИСК:");
        System.out.println("========================================================");
        System.out.println("Ключевое слово: [" + searchPhrase + "]");
        System.out.println("Размер списка продуктов: [" + list.size() + "]");
        System.out.println("========================================================");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("i: ["+i+"]");
            System.out.println("ProductId: ["+list.get(i).getProductId()+"]");
            System.out.println("Title: ["+list.get(i).getTitle()+"]");
            System.out.println("ImageUrl: ["+list.get(i).getImageUrl()+"]");
            System.out.println("Price: ["+list.get(i).getPrice()+"]");
            System.out.println("-----------------------------------------------------");
        }

        //подробная информация о товаре
        /*
        if (list.size() > 0) {
            ProductInList product = list.get(0);
            System.out.println("#####################################################################");
            System.out.println("Подробная информация о товаре:");
            System.out.println("Product ID: " + product.getProductId());
            System.out.println("#####################################################################");
            ProductInDetail productInDetail = adapter.getDetailedArticle(product.getProductId());
            System.out.println(productInDetail.toString());
            System.out.println("=====================================================================");
        }
         */
    }
}
