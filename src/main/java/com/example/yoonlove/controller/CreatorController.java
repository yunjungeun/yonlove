package com.example.yoonlove.controller;

import com.example.yoonlove.dto.CreatorDto;
import com.example.yoonlove.service.CreatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
        public String insert(){
            return "creator/creatorinsert";
        }

        @GetMapping("creator/insertcreator")
        public String insertCreator(CreatorDto creatorDto){
            System.out.println("test11");
            System.out.println(creatorDto.toString());
            creatorservice.insertCreator(creatorDto);
            return "redirect:/creator/creator";
        }
}
