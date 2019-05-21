package com.vsu.webmarket.logic.product.sources.ebay.ebayModel;

import java.util.ArrayList;
import java.util.List;

public class EBayProductInDetail {

    private String productUrl;
    private EBayProductInList productInList;
    private String description;
    private List<String> imageUrls;
    private List<String> parameters;

    public EBayProductInDetail() {
        productUrl = "";
        productInList = new EBayProductInList("", "", "", "");
        description = "";
        imageUrls = new ArrayList<>();
        parameters = new ArrayList<>();
    }

    public EBayProductInDetail(String productUrl, EBayProductInList productInList,
                               String description, List<String> imageUrls, List<String> parameters) {
        this.productUrl = productUrl;
        this.productInList = productInList;
        this.description = description;
        this.imageUrls = imageUrls;
        this.parameters = parameters;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public EBayProductInList getProductInList() {
        return productInList;
    }

    public void setProductInList(EBayProductInList productInList) {
        this.productInList = productInList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public List<String> getParameters() {
        return parameters;
    }

    public void setParameters(List<String> parameters) {
        this.parameters = parameters;
    }

    public String toString() {
        String res = "("+super.toString()+")\n";
        //begin
        res += "{\n";

        //--------------------------------------------------------------
        //[productUrl]
        //--------------------------------------------------------------
        res += "  [productUrl]\n";
        res += "    '"+productUrl+"'\n";
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
