package com.example.yoonlove.controller;

import com.example.yoonlove.dto.BudgetDto;
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
    public String insertprojectview(){
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

    // ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ프로젝트 끝

    //제작예산
    @GetMapping("/project/listbudget")
    public ModelAndView selectListBudget(){
        List<BudgetDto> dto = projectService.selectListBudget();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/project/listbudget");
        mv.addObject("selectListBudget", dto);
        return mv;
    }

    @GetMapping("project/insertbudgetview")  // 작성클릭 후 페이지 리턴하는
    public String insertbudgetview(){
        return "project/budgetinsert";
    }

    @GetMapping("project/insertbudget")   // 작성 후 입력값 넘기는~
    public String insertBudget(BudgetDto budgetDto){
        /*System.out.println("test11");*/
        /*System.out.println(projectDto.toString());*/
        projectService.insertBudget(budgetDto);
        return "redirect:/project/listbudget";
    }

    @GetMapping("project/{budget_id}/selectbudget")
    public ModelAndView selectProject(BudgetDto budgetDto){
        BudgetDto dto = projectService.selectBudget(budgetDto);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/project/selectbudget");
        mv.addObject("selectBudget", dto);
        return mv;
    }

    @GetMapping("/project/deletebudget/{budget_id}")
    public String deleteBudget(BudgetDto dto){
        projectService.deleteBudget(dto);
        return "redirect:/project/listbudget";
    }

    // 수정!!!!!!!!!!!!!!!!!!!!!
    @GetMapping("/project/updatebudgetview/{budget_id}") // 수정하는곳
    public ModelAndView updateBudgetView(BudgetDto budgetDto){
        ModelAndView mv = new ModelAndView();
        BudgetDto dto = projectService.selectBudget(budgetDto);
        mv.setViewName("/project/updatebudget");
        mv.addObject("selectBudget", dto);
        return mv;
    }

    @GetMapping("/project/updatebudget/{budget_id}") // 수정 //해당주소 머스터치에 액션값
    public String updateBudget(BudgetDto dto){
        /*System.out.println(dto.toString());*/
        projectService.updateBudget(dto);
        return "redirect:/project/listbudget";   // 목록페이지로 이동
    }
}
