package com.example.yoonlove.controller;

import com.example.yoonlove.dto.CreatorDto;
import com.example.yoonlove.service.CreatorService;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
    public class CreatorController {

        @Autowired
        private CreatorService creatorservice;
        //서비스 객체 수정

        @GetMapping("creator/creator")
        public ModelAndView selectListCreaotr(){
            List<CreatorDto> dto = creatorservice.selectListCreator();
            ModelAndView mv = new ModelAndView();
            mv.setViewName("creator/creator");
            mv.addObject("selectListCreator", dto);
            return mv;
        }

        @GetMapping("creator/insertcreatorview")
        public String insertcreatorview(){
            return "creator/creatorinsert";
        }

        @GetMapping("creator/insertcreator")
        public String insertCreator(CreatorDto creatorDto){
            System.out.println("test11");

            System.out.println(creatorDto.toString());
            creatorservice.insertCreator(creatorDto);
            return "redirect:/creator/creator";
        }

      @GetMapping("/creator/{ch_id}/selectcreator")
      public ModelAndView selectCreator(CreatorDto creatorDto){
          CreatorDto dto = creatorservice.selectCreator(creatorDto);
          ModelAndView mv = new ModelAndView();
          mv.setViewName("/creator/selectcreator");
          mv.addObject("selectCreator", dto);
          return mv;
      }

    @GetMapping("/creator/updatecreatorview/{ch_id}") // 수정하는곳
    public ModelAndView updateCreatorView(CreatorDto creatorDto){
        ModelAndView mv = new ModelAndView();
        CreatorDto dto = creatorservice.selectCreator(creatorDto);
        mv.setViewName("/creator/updatecreator");
        mv.addObject("selectCreator", dto);
        return mv;
    }

     @GetMapping("/creator/updatecreator/{ch_id}") // 수정
    public String updateCreator(CreatorDto dto){
            System.out.println(dto.toString());
        creatorservice.updateCreator(dto);
        return "redirect:/creator/creator";   // 목록페이지로 이동
    }

    @GetMapping("/creator/deletecreator/{ch_id}")
    public String deleteCreator(CreatorDto dto){
        creatorservice.deleteCreator(dto);
        return "redirect:/creator/creator";
    }
}
