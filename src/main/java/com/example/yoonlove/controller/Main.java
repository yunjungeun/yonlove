package com.example.yoonlove.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class Main {

    @GetMapping("/index")
    public String mainPage(Principal user){

        return "/main/index2";
    }


    @GetMapping("/임시")
    public String mainPage2(){
        return "/main/index";
    }

    @GetMapping("/게시판")
    public String testPage1(){
        return "/main/posttest";
    }


    @GetMapping("/로그인")
    public String testPage2(){
        return "/main/login";
    }

    @GetMapping("/헤더")
    public String testPage3(){
        return "/layout/header";
    }

    @GetMapping("/일반회원가입")
    public String testPage4(){
        return "/login/signup";
    }
}

