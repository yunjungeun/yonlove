package com.example.yoonlove.controller;

import com.example.yoonlove.dto.CreatorDto;
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

    @GetMapping("project/{project_id}/selectproject")
    public ModelAndView selectProject(ProjectDto projectDto){
        ProjectDto dto = projectService.selectProject(projectDto);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/project/selectproject");
        mv.addObject("selectProject", dto);
        return mv;

    }

    @GetMapping("project/insertprojectview")  // 작성클릭 후 페이지 리턴하는
    public String insert(){
        return "project/projectinsert";
    }

    @GetMapping("project/insertproject")   // 작성 후 입력값 넘기는~
    public String insertProject(ProjectDto projectDto){
            /*System.out.println("test11");*/
            /*System.out.println(projectDto.toString());*/
        projectService.insertProject(projectDto);
        return "redirect:/project/listproject";
    }


        // 삭제 !!!!!!!!!!!!!!!!!!
    @GetMapping("/project/deleteproject/{project_id}")
    public String deleteProject(ProjectDto dto){
         projectService.deleteProject(dto);
        return "redirect:/project/listproject";
    }


    // 수정!!!!!!!!!!!!!!!!!!!!!
    @GetMapping("/project/updateprojectview/{project_id}") // 수정하는곳
    public ModelAndView updateProjectView(ProjectDto projectDto){
        ModelAndView mv = new ModelAndView();
        ProjectDto dto = projectService.selectProject(projectDto);
        mv.setViewName("/project/updateproject");
        mv.addObject("selectProject", dto);
        return mv;
    }

    @GetMapping("/project/updateproject/{project_id}") // 수정 //해당주소 머스터치에 액션값
    public String updateCreator(ProjectDto dto){
        /*System.out.println(dto.toString());*/
        projectService.updateProject(dto);
        return "redirect:/project/listproject";   // 목록페이지로 이동
    }

}
