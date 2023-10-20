package com.example.yoonlove.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Main {

    @GetMapping("/index")
    public String mainPage(){
        return "/main/index";
    }

}
