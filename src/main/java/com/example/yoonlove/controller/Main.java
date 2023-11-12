package com.example.yoonlove.controller;

import com.example.yoonlove.dto.NoticeDto;
import com.example.yoonlove.dto.PageDto;
import com.example.yoonlove.dto.VideoDto;
import com.example.yoonlove.service.CsService;
import com.example.yoonlove.service.PagingService;
import com.example.yoonlove.service.VideoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

@Controller
public class Main {

    @Autowired
    private CsService csService;

    @Autowired
    private PagingService pagingService;


    @Autowired
    private VideoService videoService;

    @GetMapping("/index")
    public ModelAndView mainPage(Principal user, Model model, HttpSession session) throws JsonProcessingException { // throws JsonProce추가
        String userId = null;
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/main/index2");


        if(user == null){
            model.addAttribute("loggedIn", false);  // 로그인 안한 상태
        }else {
            model.addAttribute("loggedIn", true);
            userId = user.getName();
        }

        PageDto pdto = new PageDto();
        PageDto pageDto = new PageDto("notice","notice_id",1, pdto);

        //페이징정보처리 메소드
        PageDto pageInfo = pagingService.paging(pageDto);
        //검색유무에 따라 동적 페이지링크를 만들어줌
        List<NoticeDto> dto = csService.selectListNotice(pageInfo);
        mv.addObject("selectListNotice", dto);
        //페이징에 필요한센션
        mv.addObject("paging", pageInfo);  //페이징정보


        //동영상 섹션
        HashMap<String, VideoDto> bestVideo = new HashMap<>();
        if(userId != null){
           bestVideo = videoService.bestvideo(userId);
        }else {
           bestVideo = videoService.bestvideo("비회원");
        }
        //베스트 크리에이터 영상 정보 해쉬맵
        mv.addObject("bestVideo", bestVideo);
        //동영상 섹션end

        return mv;
        }


    @GetMapping("/임시")
    public String mainPage2(){
        return "/main/index2";
    }
}


