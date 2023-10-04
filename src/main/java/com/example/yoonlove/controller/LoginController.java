package com.example.yoonlove.controller;

import com.example.yoonlove.dto.CreatorDto;
import com.example.yoonlove.dto.UserDto;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    /**
     * 1. 회원가입
     *   1-1. 회원 가입 페이지
     *   1-2. 회원 가입 요청
     *     - ID, PW, 이름 기타등등
     *   1-3. 회원가입 성공 페이지
     *   1-4. 회원가입 실패 페이지(팝업....경고창)
     * 2. 로그인
     *   2-1 로그인 페이지
     *   2-2 로그인 요청
     *     - ID, PW
     *   2-3 로그인 성공시 페이지이동 (메인, 로그인페이지 이전화면)
     *   2-4 로그인 실패시 페이지 (팝업... 경고창 등등)
     */
    //1. 페이지 이동
    @GetMapping(path="/login",produces = MediaType.TEXT_HTML_VALUE)
    public String login(){
        return "/login-page";
    }

    // 로그인 요청
    @PostMapping(path="/login",produces = MediaType.TEXT_HTML_VALUE,consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView loginPro(@ModelAttribute UserDto.UserLoginRequest dto){

        return new ModelAndView("/success");
    }

    //회원가입 페이지 이동

    //회원가입 요청


}