package com.vsu.webmarket.logic.product.sources.ebay;

import com.vsu.webmarket.logic.product.sources.ebay.ebayModel.EBayProductInDetail;
import com.vsu.webmarket.logic.product.sources.ebay.ebayModel.EBayProductInList;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class EBayClient {

    public enum EBaySortType {
        HIGH_PRICE_FIRST,
        LOWER_PRICE_FIRST
    }

    public List<EBayProductInList> getSearchResult(String searchPhrase, int pageNumber) {
        return getSearchResult(searchPhrase, pageNumber, null);
    }

    public List<EBayProductInList> getSearchResult(String searchPhrase, int pageNumber, EBaySortType sortType) {
        return getSearchResult(searchPhrase, pageNumber, sortType, -1.0, -1.0);
    }

    public List<EBayProductInList> getSearchResult(String searchPhrase, int pageNumber, EBaySortType sortType, double minPriceRange, double maxPriceRange) {
        try {
            String apiServer = "http://svcs.sandbox.ebay.com/services/search/FindingService/v1";
            String serviceVersion = "1.0.0";
            String securityAppName = "Vladimir-webmarke-SBX-5f2fb35bc-854a6ab5";
            String responseDataFormat = "JSON";
            String callback = "_cb_findItemsByKeywords";
            String globalId = "EBAY-US";
            String entriesPerPage = "30"; //кол-во продуктов на страницу (от 1 до 100)
            String outputSelector  = "AspectHistogram";
            String siteId = "0";
            String keywords = searchPhrase;

            //[sort]
            //https://developer.ebay.com/devzone/finding/callref/types/SortOrderType.html
            //(optional)
            //BestMatch(default), BidCountFewest, BidCountMost, CountryAscending, CountryDescending, CurrentPriceHighest,
            //DistanceNearest, EndTimeSoonest, PricePlusShippingHighest, PricePlusShippingLowest,
            //StartTimeNewest, WatchCountDecreaseSort
            String sortOrder = "";
            if (sortType != null) {
                switch (sortType) {
                    case LOWER_PRICE_FIRST:
                        sortOrder = "&sortOrder=PricePlusShippingLowest";
                        break;
                    case HIGH_PRICE_FIRST:
                        sortOrder = "&sortOrder=CurrentPriceHighest";
                        break;
                    default:
                        sortOrder = "&sortOrder=BestMatch";
                }
            }
            //[filter]
            //https://developer.ebay.com/devzone/finding/callref/types/ItemFilterType.html
            String itemFilter = "";
            int n = 0;
            //фильтр удаление дубликатов
            itemFilter += "&itemFilter("+n+").name=HideDuplicateItems&itemFilter("+n+").value=true";
            //диапазон цены
            if (minPriceRange >= 0 && maxPriceRange >=0 && minPriceRange <= maxPriceRange) {
                //фильтр для отображения в рамках диапазона установленной цены
                itemFilter += "&itemFilter("+(++n)+").name=ListingType&itemFilter("+n+").value(0)=FixedPrice&itemFilter("+n+").value(1)=StoreInventory";
                //фильтр минимальной цены
                itemFilter += "&itemFilter("+(++n)+").name=MinPrice&itemFilter("+n+").value="+minPriceRange;
                //фильтр максимальной цены
                itemFilter += "&itemFilter("+(++n)+").name=MaxPrice&itemFilter("+n+").value="+maxPriceRange;
            }

            keywords = URLEncoder.encode(keywords, "UTF-8");
            String url = apiServer+"?OPERATION-NAME=findItemsByKeywords"+
                    "&SERVICE-VERSION="+serviceVersion+
                    "&SECURITY-APPNAME="+securityAppName+
                    "&RESPONSE-DATA-FORMAT="+responseDataFormat+
                    "&callback="+callback+
                    "&GLOBAL-ID="+globalId+
                    "&siteid="+siteId+
                    "&paginationInput.entriesPerPage="+entriesPerPage+
                    "&paginationInput.pageNumber="+pageNumber+
                    "&outputSelector="+outputSelector+
                    sortOrder+
                    itemFilter+
                    "&REST-PAYLOAD="+"true"+
                    "&keywords="+keywords;

            URLConnection connection = new URL(url).openConnection();
            String line;
            String response;

            StringBuilder builder = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }

            response = builder.toString();

            //Проверка, являеться ли строка - JSON
            try {
                new JSONObject(response);
            } catch (JSONException e) {
                try {
                    new JSONArray(response);
                } catch (JSONException e1) {
                    return new ArrayList<>();
                }
            }

            //извлечение информации
            JSONObject obj = new JSONObject(response);
            if (obj.has("findItemsByKeywordsResponse")) {
                JSONArray arr = obj.getJSONArray("findItemsByKeywordsResponse");
                obj = arr.getJSONObject(0);

                List<EBayProductInList> productInLists = new ArrayList<>();
                if (obj.has("ack") && obj.getJSONArray("ack").getString(0).equalsIgnoreCase("Success") && obj.has("searchResult")) {
                    obj = obj.getJSONArray("searchResult").getJSONObject(0);

                    if (obj.has("item")) {
                        arr = obj.getJSONArray("item");

                        for (int i = 0; i < arr.length(); i++) {
                            obj = arr.getJSONObject(i);

                            //productId
                            String productId = "";
                            if (obj.has("itemId")) {
                                productId = obj.getJSONArray("itemId").getString(0);
                            }

                            //title
                            String title = "";
                            if (obj.has("title")) {
                                title = obj.getJSONArray("title").getString(0);// + " | " + obj.getJSONArray("viewItemURL").getString(0);
                            }

                            //galleryURL
                            String galleryURL = "";
                            if (obj.has("galleryURL")) {
                                galleryURL = obj.getJSONArray("galleryURL").getString(0);
                            }

                            //price
                            String price = "";
                            if (obj.has("sellingStatus")) {
                                JSONObject obj1 = obj.getJSONArray("sellingStatus").getJSONObject(0);
                                if (obj1.has("currentPrice")) {
                                    obj1 = obj1.getJSONArray("currentPrice").getJSONObject(0);

                                    if (obj1.has("@currencyId") && obj1.has("__value__")) {
                                        String currencyId = obj1.getString("@currencyId");
                                        String value = obj1.getString("__value__");
                                        price = currencyId + " " + value;
                                    }
                                }
                            }

                            //add in list
                            productInLists.add(new EBayProductInList(productId, title, galleryURL, price));
                        }

                        return productInLists;
                    }
                }
            }
        } catch (Exception ignored) {
        }

        return new ArrayList<>();
    }

    public EBayProductInDetail getDetailedArticle(String productId) {
        try {
            //productId = "110396037154"; //one image (for test)
            //productId = "110396188361"; //some images

            String url = "https://api.sandbox.ebay.com/ws/api.dll";
            String ebayAuthToken = "AgAAAA**AQAAAA**aAAAAA**GAWvXA**nY+sHZ2PrBmdj6wVnY+sEZ2PrA2dj6wFk4aiCZCBoA6dj6x9nY+seQ**bPgEAA**AAMAAA**kzBAFgcj1tsipFbnwLkNzFkwsZATVSHvYVJs3aI8evf03VopbslSnGALytUE9fYaO4oAIXKioJgHhX8dKmgs6TohBLPMZy1OSSQpyP8Se5n1lBvJt+hKUrTSFJ4GedmW5WJa5kcBY5NtS2C4r8RgAqAtUOrNEx6e6iROJ+owoNpwflN8Gk2NQEkvcIee25nhLCi1wVqCCI05mCapPelSlOmdi90J6dfvp1H3d+rJ6I6kyGNdQ+ffl4d1k4AE/+IObiSvK0ztBm3E832nF+MYl1LwBuRrQgmEscpy/T4RejXeO3ncTyqOIiY5IftufSK456PpylDnFQ3yDyGR6El6mbykV6tvpYknQlInmsyMQgK3C8f4KUDUWGGiQq4BgphA1vaBSsLE9rtZoWMFAIdpBz7KVAbTWKQr+b/MAndirws1sNva7/m4nEy/rkKdRdUZXUziWLZMc9vSTICszRbls+wBdR9FP4CA5jAvy0JoeNCFVJeGQONcYQiFt5GqCdcTMvWYQOlCIq/ZzPZjOoHIrz+GBV+KgTh+8ayDteaXW5tlDh0/jBxc5dUiOeS99g9gD+54o/dhIeZ+gbW37/5oZIaMX+FtgyfdJX0Dir1cy3ZiAFPlpzi/A09+AMiMv1IpZRWExyKd2XRE8x8SdBWTkoVh2ekaLARcCiZbF184UjBeIk/QnbltRcYIcH+1PqQnItNsCac/DKwcImf0HUcoCFtDuwdbjikuO8NEoFIHthkXkkLupi5K26U6Sz/0VxJU";
            String detailLevel = "ReturnAll"; // (optional) [ItemReturnAttributes, ItemReturnDescription, ReturnAll]
            String version = "1081";
            String callName = "GetItem";
            String siteId = "0";
            String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<GetItemRequest xmlns=\"urn:ebay:apis:eBLBaseComponents\">\n" +
                    "    <RequesterCredentials xmlns=\"urn:ebay:apis:eBLBaseComponents\">\n" +
                    "        <eBayAuthToken>"+ebayAuthToken+"</eBayAuthToken>\n" +
                    "    </RequesterCredentials>\n" +
                    "    <ItemID>"+productId+"</ItemID>\n" +
                    "    <DetailLevel>"+detailLevel+"</DetailLevel>\n" +
                    "    <IncludeItemSpecifics>true</IncludeItemSpecifics>\n" +
                    "    <Version>"+version+"</Version>\n" +
                    "</GetItemRequest>";
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .header("X-EBAY-API-COMPATIBILITY-LEVEL", version)
                    .header("X-EBAY-API-CALL-NAME", callName)
                    .header("X-EBAY-API-SITEID", siteId)
                    .post(RequestBody.create(MediaType.parse("text/xml;charset=utf-8"), xml))
                    .url(url)
                    .build();
            Response resp = client.newCall(request).execute();
            String response = resp.body().string();

            //TODO конвертирование XML в JSON как мера для быстрого написания кода, с XML-парсером еще плохо работал
            try {
                JSONObject xmlJSONObj = XML.toJSONObject(response);
                response = xmlJSONObj.toString();
            } catch (JSONException e) {
                return new EBayProductInDetail();
            }

            //извлечение информации
            JSONObject obj = new JSONObject(response);
            if (obj.has("GetItemResponse")) {
                obj = obj.getJSONObject("GetItemResponse");
                //если запрос без ошибок
                if (obj.has("Item") && obj.has("Ack") && obj.getString("Ack").equalsIgnoreCase("Success")) {
                    obj = obj.getJSONObject("Item");

                    //------------------------------------------------------------
                    //[webUrl] - Получение данных для поля ProductInList
                    //------------------------------------------------------------
                    //webUrl
                    String webUrl = "";
                    if (obj.has("ListingDetails")) {
                        JSONObject obj1 = obj.getJSONObject("ListingDetails");
                        if (obj1.has("ViewItemURL")) {
                            webUrl = obj1.getString("ViewItemURL");
                        }
                    }

                    //------------------------------------------------------------
                    //[ProductInList]
                    //------------------------------------------------------------
                    //title
                    String title = "";
                    if (obj.has("Title")) {
                        title = obj.getString("Title");
                    }

                    //imageURL
                    String imageURL = "";
                    if (obj.has("PictureDetails")) {
                        JSONObject obj1 = obj.getJSONObject("PictureDetails");
                        if (obj1.has("GalleryURL")) {
                            imageURL = obj1.getString("GalleryURL");
                        }
                    }

                    //price
                    String price = "";
                    if (obj.has("SellingStatus")) {
                        JSONObject obj1 = obj.getJSONObject("SellingStatus");
                        if (obj1.has("CurrentPrice")) {
                            obj1 = obj1.getJSONObject("CurrentPrice");
                            if (obj1.has("currencyID") && obj1.has("content")) {
                                String currencyId  = obj1.getString("currencyID");
                                double content = obj1.getDouble("content");
                                price = currencyId+" "+content;
                            }
                        }
                    }

                    EBayProductInList productInList = new EBayProductInList(productId, title, imageURL, price);

                    //------------------------------------------------------------
                    //[description]
                    //------------------------------------------------------------
                    //description
                    String description = "";
                    if (obj.has("Description")) {
                        description = obj.getString("Description");
                    }

                    //------------------------------------------------------------
                    //[imageUrls]
                    //------------------------------------------------------------
                    //imageUrls
                    List<String> imageUrls = new ArrayList<>();
                    imageUrls.add(imageURL); //добавляем основное изображение
                    if (obj.has("PictureDetails")) {
                        JSONObject obj1 = obj.getJSONObject("PictureDetails");
                        if (obj1.has("PictureURL")) { //если есть дополнительные картинки
                            try {
                                JSONArray arr = obj1.getJSONArray("PictureURL");
                                for (int i = 0; i < arr.length(); i++) {
                                    String pictureURL = arr.getString(i);
                                    imageUrls.add(pictureURL);
                                }
                            } catch (JSONException ignored) {
                            }
                        }
                    }

                    //------------------------------------------------------------
                    //[parameters]
                    //------------------------------------------------------------
                    //parameters
                    List<String> parameters = new ArrayList<>();
                    if (obj.has("ItemSpecifics")) {
                        JSONObject obj1 = obj.getJSONObject("ItemSpecifics");
                        if (obj1.has("NameValueList")) {
                            try {
                                JSONArray arr = obj1.getJSONArray("NameValueList");
                                for (int i = 0; i < arr.length(); i++) {
                                    JSONObject obj2 = arr.getJSONObject(i);
                                    if (obj2.has("Source") && obj2.getString("Source").equalsIgnoreCase("ItemSpecific") &&
                                            obj2.has("Name") && obj2.has("Value")) {
                                        String name = obj2.getString("Name");
                                        String value = obj2.getString("Value");
                                        parameters.add(name+": "+value);
                                    }
                                }
                            } catch(JSONException ignored) {
                            }
                        }
                    }

                    //return detail item
                    return new EBayProductInDetail(webUrl, productInList, description, imageUrls, parameters);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new EBayProductInDetail();
    }
}
