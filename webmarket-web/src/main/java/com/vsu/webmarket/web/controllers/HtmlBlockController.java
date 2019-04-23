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
}
