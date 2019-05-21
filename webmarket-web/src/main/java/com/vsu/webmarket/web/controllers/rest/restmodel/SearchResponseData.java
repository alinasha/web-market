package com.vsu.webmarket.web.controllers.rest.restmodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SearchResponseData implements Serializable {
    private List<SearchResponseItem> data = new ArrayList<>();

    public List<SearchResponseItem> getData() {
        return data;
    }

    public void setData(List<SearchResponseItem> data) {
        this.data = data;
    }
}
