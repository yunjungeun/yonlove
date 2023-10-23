package com.example.yoonlove.controller;

import com.example.yoonlove.dto.BudgetDto;
import com.example.yoonlove.dto.ProduceDto;
import com.example.yoonlove.dto.ProjectDto;
import com.example.yoonlove.dto.SearchDto;
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
        System.out.println("test");
        System.out.println(projectDto.toString());
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
        projectService.updateProject(dto);
        return "redirect:/project/listproject";   // 목록페이지로 이동
    }
    // ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ프로젝트 끝

    //제작예산
    // httpserveltrequest -> @RequestParam -> Command Object (dto)
    @GetMapping("/project/listbudget")
    public ModelAndView selectListBudget(SearchDto searchDto){
        List<BudgetDto> dto = projectService.selectListBudget(searchDto);
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

    @GetMapping("/project/updatebudgetview/{budget_id}") // 수정하는곳
    public ModelAndView updateBudgetView(BudgetDto budgetDto){
        ModelAndView mv = new ModelAndView();
        BudgetDto dto = projectService.selectBudget(budgetDto);
        mv.setViewName("/project/updatebudget");
        mv.addObject("selectBudget", dto);
        return mv;
    }

    @GetMapping("/project/updatebudget/{budget_id}") // 수정-해당주소 머스터치에 액션값
    public String updateBudget(BudgetDto dto){
        projectService.updateBudget(dto);
        return "redirect:/project/listbudget";   // 목록페이지로 이동
    }
                         // =====================budget 완료 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!


    @GetMapping("/project/listproduce")   //목록
    public ModelAndView selectListProduce(){
        List<ProduceDto> dto = projectService.selectListProduce();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/project/listproduce");
        mv.addObject("selectListProduce", dto);
        return mv;
    }

    @GetMapping("project/insertproduceview")  // 작성클릭 후 페이지 리턴하는
    public String insertproduceview(){
        return "project/produceinsert";
    }

    @GetMapping("project/insertproduce")   // 작성 후 입력값 넘기는~ , 추가
    public String insertProduce(ProduceDto produceDto){
        projectService.insertProduce(produceDto);
        return "redirect:/project/listproduce";}

    @GetMapping("project/{pd_id}/selectproduce")   //상세보기
    public ModelAndView selectProduce(ProduceDto produceDto){
        ProduceDto dto = projectService.selectProduce(produceDto);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/project/selectproduce");
        mv.addObject("selectProduce", dto);
        return mv;
    }
    @GetMapping("project/deleteproduce/{pd_id}")
    public String deleteProduce(ProduceDto dto){    //삭제
        projectService.deleteProduce(dto);
        return "redirect:/project/listproduce";
    }
    @GetMapping("project/updateproduceview/{pd_id}") // 수정
    public ModelAndView updateProduceView(ProduceDto produceDto){
        ModelAndView mv = new ModelAndView();
        ProduceDto dto = projectService.selectProduce(produceDto);
        mv.setViewName("/project/updateproduce");
        mv.addObject("selectProduce", dto);
        return mv;
    }
    @GetMapping("project/updateproduce/{pd_id}") // 수정-해당주소 머스터치에 액션값
    public String updateProduce(ProduceDto dto){
        projectService.updateProduce(dto);
        return "redirect:/project/listproduce"; }
                    // 프로듀스 제작 완료 !!!!!!!!============================================
}











