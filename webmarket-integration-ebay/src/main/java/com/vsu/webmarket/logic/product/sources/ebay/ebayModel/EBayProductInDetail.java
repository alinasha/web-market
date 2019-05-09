package com.vsu.webmarket.logic.product.sources.ebay.ebayModel;

import java.util.ArrayList;
import java.util.List;

public class EBayProductInDetail {

    private String webUrl;
    private EBayProductInList productInList;
    private String description;
    private List<String> imageUrls;
    private List<String> parameters;

    public EBayProductInDetail() {
        webUrl = "";
        productInList = new EBayProductInList("", "", "", "");
        description = "";
        imageUrls = new ArrayList<>();
        parameters = new ArrayList<>();
    }

    public EBayProductInDetail(String webUrl, EBayProductInList productInList,
                               String description, List<String> imageUrls, List<String> parameters) {
        this.webUrl = webUrl;
        this.productInList = productInList;
        this.description = description;
        this.imageUrls = imageUrls;
        this.parameters = parameters;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
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
