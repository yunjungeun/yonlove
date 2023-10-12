package com.example.yoonlove.controller;

import com.example.yoonlove.dto.VideoDto;
import com.example.yoonlove.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class VideoController {

    @Autowired
    private VideoService videoService;

    @GetMapping("vd/contentList") //컨텐츠 전체목록보기 뷰
    public ModelAndView selectListContent() {
        List<VideoDto> contentList = videoService.selectListContent();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("video/contentList");
        mv.setStatus(HttpStatus.valueOf(200));
        mv.addObject("selectContentList", contentList);
        return mv;
    }

    @GetMapping("vd/content/{video_id}") //해당 컨텐츠 뷰
    public ModelAndView selectContent(VideoDto dto) {
        VideoDto contentDetail = videoService.selectContent(dto);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("/video/contentDetail");
        mv.setStatus(HttpStatus.valueOf(200));
        mv.addObject("content", contentDetail);
        return mv;
    }


    /* 127.0.0.1:8080/bbs/1?abc=123
     * ?전: URL / ?후: 파라미터
     * URL에 있는 1을 꺼낼 때 end-point에 {}를 사용. 즉,{bbsNo} -> 1
     *
     * */


    @GetMapping("vd/contentInsertView")  //컨텐츠 추가하는 뷰
    public ModelAndView selectVideoInsertView() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/video/contentInsertView");
        mv.setStatus(HttpStatus.valueOf(200));
        return mv;
    }


    @GetMapping("vd/contentInsert")  //컨텐츠 추가 처리
    public String insertContent(VideoDto dto) {
        System.out.println(dto.toString());
        videoService.insertContent(dto);
        System.out.println(dto.toString());
        return "redirect:/vd/contentList";
    }

    @GetMapping("vd/{video_id}/UpdateView") //컨텐츠 업데이트하는 뷰
    public ModelAndView selectContentUpdateView( VideoDto dto) {
        VideoDto videodto = videoService.selectContent(dto);//업데이트를 하려면 해당 컨텐츠 불러와야하니까 위에 selectContent메소드를 다시씀!
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/video/contentUpdateView");
        mv.setStatus(HttpStatus.valueOf(200));
        mv.addObject("updateContent", videodto);
        return mv;
    }


    @GetMapping("vd/{video_id}/updateContent") //업데이트 처리
    public String updateContent( VideoDto dto) {
        videoService.updateContent(dto);
        return "redirect:/vd/contentList";
    }


    @GetMapping("vd/{video_id}/delete") //삭제 처리
    public String deleteContent( VideoDto dto) {
        videoService.deleteContent(dto);
        return "redirect:/vd/contentList";

    }
}