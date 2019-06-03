package com.vsu.webmarket.web.controllers;

import com.google.gson.Gson;
import com.vsu.webmarket.ApplicationContextProvider;
import com.vsu.webmarket.data.repositories.HistoryRepository;
import com.vsu.webmarket.data.services.HistoryService;
import com.vsu.webmarket.logic.product.productmodel.ProductInDetail;
import com.vsu.webmarket.model.History;
import com.vsu.webmarket.web.controllers.rest.restmodel.ErrorData;
import org.springframework.context.ApplicationContext;
import com.vsu.webmarket.data.repositories.UserRepository;
import com.vsu.webmarket.data.services.UserService;
import com.vsu.webmarket.logic.product.ProductSource;
import com.vsu.webmarket.logic.product.productmodel.ProductInList;
import com.vsu.webmarket.logic.product.sources.adapters.ebay.EBayProductSourceAdapter;
import com.vsu.webmarket.logic.product.sources.ebay.EBayClient;
import com.vsu.webmarket.logic.product.sources.ebay.ebayModel.EBayProductInDetail;
import com.vsu.webmarket.logic.product.sources.ebay.ebayModel.EBayProductInList;
import com.vsu.webmarket.logic.services.AuthService;
import com.vsu.webmarket.logic.services.ProductSourceRecordService;
import com.vsu.webmarket.model.User;
import com.vsu.webmarket.web.controllers.rest.restmodel.SearchData;
import com.vsu.webmarket.web.controllers.rest.restmodel.SearchResponseData;
import com.vsu.webmarket.web.controllers.rest.restmodel.SearchResponseItem;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/data")
public class DataRestController {
    private final Gson gson = new Gson();
    private AuthService authService;
    private final EBayProductSourceAdapter productSource = new EBayProductSourceAdapter(new EBayClient());

    public DataRestController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "/test", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getTestData(@RequestBody ModelMap attributes, HttpServletResponse response) {
        //attributes.get("text")
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

    @GetMapping(value = "/product_detailed", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getProductDetailed(HttpServletRequest request, @RequestParam("id") final String id, HttpServletResponse response) {
        //custom valid productId
        long productId = 0;
        try {
            productId = Long.parseLong(id);
        } catch (Exception ignored) {
        }
        if (productId > 0) {

            //get cookies from client
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if (cookie != null && cookie.getName() != null && cookie.getName().equalsIgnoreCase("token")) {
                    //get user token
                    String userToken = cookie.getValue();
                    Optional<String> usernameByToken = authService.getUsernameByToken(userToken);
                    if (usernameByToken.isPresent()) {
                        //get user by userToken
                        ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();
                        UserService userService = applicationContext.getBean(UserService.class);
                        UserRepository userRepository = userService.getRepository();
                        Optional<User> optionalUser = userRepository.findByToken(userToken);
                        if (optionalUser.isPresent()) {
                            //get recordService
                            User user = optionalUser.get();
                            EBayClient origin = new EBayClient();
                            ProductSource adapter = new EBayProductSourceAdapter(origin);
                            ProductSource productSourceRecordService = new ProductSourceRecordService(adapter, user);

                            //record
                            ProductInDetail productInDetail = productSourceRecordService.getDetailedArticle(productId+"");

                            //response
                            response.setStatus(200);
                            return gson.toJson(productInDetail);
                        }
                    }
                }
            }
        }
        return "{ \"data\": [] }";
    }

    @GetMapping(value = "/user_history", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getUserHistory(HttpServletRequest request, HttpServletResponse response) {
        //get cookies from client
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie != null && cookie.getName() != null && cookie.getName().equalsIgnoreCase("token")) {
                //get user token
                String userToken = cookie.getValue();
                Optional<String> usernameByToken = authService.getUsernameByToken(userToken);
                if (usernameByToken.isPresent()) {
                    //get user by userToken
                    ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();
                    UserService userService = applicationContext.getBean(UserService.class);
                    UserRepository userRepository = userService.getRepository();
                    Optional<User> optionalUser = userRepository.findByToken(userToken);
                    if (optionalUser.isPresent()) {
                        //get history
                        User user = optionalUser.get();
                        HistoryRepository historyRepository = ApplicationContextProvider.getApplicationContext().getBean(HistoryService.class).getRepository();
                        List<History> histories = historyRepository.getAllByUser_Id(user.getId());

                        //удаление лишних записей со списка
                        //while (histories.size() > 30) {
                        //    histories.remove(0);
                        //}

                        String responseText = "[";
                        for (History history : histories) {
                            Date date = history.getLastViewed().getTime();
                            DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd.MM.yyyy");
                            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                            //dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
                            String strDate = dateFormat.format(date);
                            responseText +=
                                    "{" +
                                            "\"id\":"+history.getId()+","+
                                            "\"lastViewed\":\""+strDate+"\","+
                                            "\"article_id\":"+history.getArticle().getId()+","+
                                            "\"article_image_url\":\""+history.getArticle().getImageUrl()+"\","+
                                            "\"article_price\":\""+history.getArticle().getPrice()+"\","+
                                            "\"article_product_url\":\""+history.getArticle().getProductUrl()+"\","+
                                            "\"article_title\":\""+history.getArticle().getTitle()+"\""+
                                            "},";
                        }
                        responseText = responseText.substring(0, responseText.length()-1);
                        responseText += "]";

                        return responseText;
                    }
                }
            }
        }

        return gson.toJson(new ErrorData("user is not authorized."));
    }
}
