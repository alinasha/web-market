package com.vsu.webmarket.logic.product.productmodel;

import java.util.ArrayList;
import java.util.List;

public class ProductInDetailImpl implements ProductInDetail {
    private String webUrl;
    private ProductInList productInList;
    private String description;
    private List<String> imageUrls;
    private List<String> parameters;

    public ProductInDetailImpl() {
        webUrl = "";
        productInList = new ProductInListImpl("", "", "", "");
        description = "";
        imageUrls = new ArrayList<>();
        parameters = new ArrayList<>();
    }

    public ProductInDetailImpl(String webUrl, ProductInList productInList, String description, List<String> imageUrls, List<String> parameters) {
        this.webUrl = webUrl;
        this.productInList = productInList;
        this.description = description;
        this.imageUrls = imageUrls;
        this.parameters = parameters;
    }

    @Override
    public String getWebUrl() {
        return webUrl;
    }

    @Override
    public ProductInList getProductListInfo() {
        return productInList;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public List<String> getImageUrls() {
        return imageUrls;
    }

    @Override
    public List<String> getParameters() {
        return parameters;
    }

    @Override
    public String toString() {
        String res = "("+super.toString()+")\n";
        //begin
        res += "{\n";

        //--------------------------------------------------------------
        //[webUrl]
        //--------------------------------------------------------------
        res += "  [webUrl]\n";
        res += "    '"+webUrl+"'\n";
        res += "\n";

        //--------------------------------------------------------------
        //[productInList]
        //--------------------------------------------------------------
        res += "  [productInList]\n";
        res += "    productId = ["+productInList.getProductId()+"]\n";
        res += "    title = ["+productInList.getTitle()+"]\n";
        res += "    imageUrl = ["+productInList.getImageUrl()+"]\n";
        res += "    price = ["+productInList.getPrice()+"]\n";
        res += "\n";

        //--------------------------------------------------------------
        //[description]
        //--------------------------------------------------------------
        res += "  [description]\n";
        res += "    '"+description+"'\n";
        res += "\n";

        //--------------------------------------------------------------
        //[imageUrls]
        //--------------------------------------------------------------
        res += "  [imageUrls]\n";
        for (String img : imageUrls) {
            res += "    imageUrl: ["+img+"]\n";
        }
        res += "\n";

        //--------------------------------------------------------------
        //[parameters]
        //--------------------------------------------------------------
        res += "  [parameters]\n";
        for (String pr : parameters) {
            res += "    parameter: ["+pr+"]\n";
        }

        //end
        res += "}\n";

        return res;
    }
}
