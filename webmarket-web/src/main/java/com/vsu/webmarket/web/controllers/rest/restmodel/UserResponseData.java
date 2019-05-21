package com.vsu.webmarket.web.controllers.rest.restmodel;

import java.io.Serializable;

public class UserResponseData implements Serializable {
    private String username;

    public UserResponseData() {
    }

    public UserResponseData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
