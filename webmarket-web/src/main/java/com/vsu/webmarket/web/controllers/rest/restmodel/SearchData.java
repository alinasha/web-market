package com.vsu.webmarket.web.controllers.rest.restmodel;

import java.io.Serializable;

public class SearchData implements Serializable {
    private String phrase;

    public SearchData() {
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }
}
