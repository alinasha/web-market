package com.vsu.webmarket.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/html")
public class HtmlBlockController {

    @GetMapping("/signin_form")
    public String getSignInForm(){
        return "signin_form";
    }

    @GetMapping("/signup_form")
    public String getSignUpForm(){
        return "signup_form";
    }

    @GetMapping("/result_list")
    public String getResultList() {
        return "result_list";
    }

    @GetMapping("/result_list_item")
    public String getResultListItem() {
        return "result_list_item";
    }

    @GetMapping("/result_detailed_view")
    public String getResultDetailedView() {
        return "result_detailed_view";
    }

    @GetMapping("/history_view")
    public String getHistoryView() {
        return "user_history";
    }

    @GetMapping("/user_info")
    public String getUserInfo() {
        return "user_info";
    }
}
