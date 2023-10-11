package com.example.yoonlove.controller;

import com.example.yoonlove.dto.ProjectDto;
import com.example.yoonlove.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@Controller
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    //프로젝트(기획)
    @GetMapping("project/listproject")
    public ModelAndView selectListProject(){
        List<ProjectDto> dto = projectService.selectListProject();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("project/listproject");
        mv.addObject("selectListProject", dto);
        return mv;
    }


}
