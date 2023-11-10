package com.example.yoonlove.controller;

import com.example.yoonlove.dto.NoticeDto;
import com.example.yoonlove.dto.PageDto;
import com.example.yoonlove.service.CsService;
import com.example.yoonlove.service.PagingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
public class Main {

    @Autowired
    private CsService csService;

    @Autowired
    private PagingService pagingService;

    @GetMapping("/index")
    public ModelAndView mainPage(Principal user, Model model, HttpSession session) throws JsonProcessingException { // throws JsonProce추가
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/main/index2");

        if(user == null){
            model.addAttribute("loggedIn", false);  // 로그인 안한 상태
        }else {
            model.addAttribute("loggedIn", true);
        }



   PageDto pdto = new PageDto();
        PageDto pageDto = new PageDto("notice","notice_id",1, pdto);

        //페이징정보처리 메소드
        PageDto pageInfo = pagingService.paging(pageDto);

        //뷰페이지에 하단 페이징처리를 해주는 리스트
        List<PageDto> pagelist = pagingService.pageList(pageInfo.getPageStart(), pageInfo.getPageEnd(), 1);

        //검색유무에 따라 동적 페이지링크를 만들어줌
        String rink = pagingService.pageRink(pageDto);
        List<NoticeDto> dto = csService.selectListNotice(pageInfo);
        mv.addObject("selectListNotice", dto);

        //페이징에 필요한센션
        mv.addObject("paging", pageInfo);  //페이징정보
        mv.addObject("pagelist", pagelist); //페이지 하단부 페이지 리스트

        return mv;
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

