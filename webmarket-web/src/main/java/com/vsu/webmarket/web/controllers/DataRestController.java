package com.vsu.webmarket.web.controllers;

import com.google.gson.Gson;
import com.vsu.webmarket.logic.product.productmodel.ProductInList;
import com.vsu.webmarket.logic.product.sources.adapters.ebay.EBayProductSourceAdapter;
import com.vsu.webmarket.logic.product.sources.ebay.EBayClient;
import com.vsu.webmarket.web.controllers.rest.restmodel.SearchData;
import com.vsu.webmarket.web.controllers.rest.restmodel.SearchResponseData;
import com.vsu.webmarket.web.controllers.rest.restmodel.SearchResponseItem;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/data")
public class DataRestController {
    private final Gson gson = new Gson();
    private final EBayProductSourceAdapter productSource = new EBayProductSourceAdapter(new EBayClient());

    @PostMapping("/test")
    @ResponseBody
    public String getTestData(@RequestBody String json, HttpServletResponse response) {
        response.setStatus(200);
        return " { \"data\" : [" +
                "{" +
                "\"id\": 110402180436," +
                "\"title\": \"iPhone 6, 6 Plus Power On/Off, Gold, 4 in 1  0509\"," +
                "\"price\": \"USD 15.99\"" +
                "}," +
                "{" +
                "\"id\": 110395947609," +
                "\"title\": \"iPhone 6 Apple 23\"," +
                "\"image\": \"http://thumbs2.sandbox.ebaystatic.com/pict/1103959476094040_0.jpg\"," +
                "\"price\": \"USD 11.0\"" +
                "}," +
                "{" +
                "\"id\": 110396359256," +
                "\"title\": \"iPhone 6 6s Front Screen Glass Lens, Black, Generic 0426\"," +
                "\"price\": \"USD 10.55\"" +
                "}" +
                "]}";
    }

    @PostMapping("/search")
    @ResponseBody
    public String getSearchResponseData(@RequestBody String json, HttpServletResponse response) {
        response.setStatus(200);
        SearchData searchData = gson.fromJson(json, SearchData.class);
        if (searchData.getPhrase() != null) {
            List<ProductInList> searchResult =
                    productSource.getSearchResult(searchData.getPhrase(), 1);
            List<SearchResponseItem> searchResponseItems
                    = new ArrayList<>(searchResult.size());
            for (ProductInList searchItem :
                    searchResult) {
                if (searchItem.getImageUrl() != null && !searchItem.getImageUrl().isEmpty()) {
                    searchResponseItems.add(new SearchResponseItem(searchItem.getProductId(),
                            searchItem.getTitle(), searchItem.getPrice(), searchItem.getImageUrl()));
                } else {
                    searchResponseItems.add(new SearchResponseItem(searchItem.getProductId(),
                            searchItem.getTitle(), searchItem.getPrice()));
                }
            }
            SearchResponseData data = new SearchResponseData();
            data.setData(searchResponseItems);
            return gson.toJson(data);
        }
        return "{ \"data\": [] }";
    }
}
