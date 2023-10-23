package com.example.yoonlove.controller;

import com.example.yoonlove.dto.*;
import com.example.yoonlove.service.PagingService;
import com.example.yoonlove.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@Controller
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private PagingService pagingService;
    //페이징처리를 위해 불러옴

    //프로젝트(기획)
    @GetMapping("project/project")   // 리스트목록!!!!!
    public ModelAndView selectListProject(PageDto pdto, @RequestParam(name="page", defaultValue = "1") int page){
        PageDto pageDto = new PageDto("project", "project_id", page, pdto);
        PageDto pageInfo = pagingService.paging(pageDto);

        List<PageDto> pageList = pagingService.pageList(pageInfo.getPageStart(), pageInfo.getPageEnd(), page);
        String rink = pagingService.pageRink(pageDto);

        List<ProjectDto> dto = projectService.selectListProject(pageInfo);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("project/listproject");
        mv.addObject("selectListProject", dto);

        mv.addObject("prefixUrl", "project");  // 컨트롤러 앞부분 이름
        mv.addObject("paging", pageInfo);  //페이징정보
        mv.addObject("pagelist", pageList); //페이지 하단부 페이지 리스트
        mv.addObject("pageRink",rink); //검색유무에 다라 동적 페이지링크를 뷰페이지에 전달
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
        projectService.insertProject(projectDto);
        return "redirect:/project/project";
    }

        // 삭제 !!!!!!!!!!!!!!!!!!
    @GetMapping("/project/deleteproject/{project_id}")
    public String deleteProject(ProjectDto dto){
         projectService.deleteProject(dto);
        return "redirect:/project/project";
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
    public String updateProject(ProjectDto dto){
        projectService.updateProject(dto);
        return "redirect:/project/project";   // 목록페이지로 이동
    }
    // ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ프로젝트 끝

    //제작예산
    // httpserveltrequest -> @RequestParam -> Command Object (dto)
    @GetMapping("/project/budget")   // ddl 테이블명
        public ModelAndView selectListBudget(PageDto pdto, @RequestParam(name="page", defaultValue = "1") int page){
        PageDto pageDto = new PageDto("budget", "budget_id", page, pdto);
        PageDto pageInfo = pagingService.paging(pageDto);

        List<PageDto> pageList = pagingService.pageList(pageInfo.getPageStart(),pageInfo.getPageEnd(),page);
        String rink = pagingService.pageRink(pageDto);

        List<BudgetDto> dto = projectService.selectListBudget(pageInfo);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/project/listbudget");  // 머스터치 이름
        mv.addObject("selectListBudget", dto);

        mv.addObject("prefixUrl", "project");  //컨트롤러 이름
        mv.addObject("paging", pageInfo);  //페이징정보
        mv.addObject("pagelist", pageList); //페이지 하단부 페이지 리스트
        mv.addObject("pageRink",rink); //검색유무에 다라 동적 페이지링크를 뷰페이지에 전달
        return mv;
    }

    @GetMapping("project/insertbudgetview")  // 작성클릭 후 페이지 리턴하는
    public String insertbudgetview(){
        return "project/budgetinsert";
    }

    @GetMapping("project/insertbudget")   // 작성 후 입력값 넘기는~
    public String insertBudget(BudgetDto budgetDto){
        projectService.insertBudget(budgetDto);
        return "redirect:/project/budget";
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
        return "redirect:/project/budget";
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
        System.out.println(dto.toString());

        projectService.updateBudget(dto);
        return "redirect:/project/budget";   // 목록페이지로 이동
    }
                         // =====================budget 완료 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!


    @GetMapping("/project/produce")   //목록
    public ModelAndView selectListProduce(PageDto pdto, @RequestParam(name="page", defaultValue = "1")int page){
        PageDto pageDto = new PageDto("produce", "pd_id", page,pdto);
        PageDto pageInfo = pagingService.paging(pageDto);

        List<PageDto> pageList = pagingService.pageList(pageInfo.getPageStart(),pageInfo.getPageEnd(),page);
        String rink = pagingService.pageRink(pageDto);

        List<ProduceDto> dto = projectService.selectListProduce(pageInfo);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/project/listproduce");
        mv.addObject("selectListProduce", dto);

        mv.addObject("prefixUrl", "project"); //컨트롤러 이름
        mv.addObject("paging", pageInfo);  //페이징정보
        mv.addObject("pagelist", pageList); //페이지 하단부 페이지 리스트
        mv.addObject("pageRink",rink); //검색유무에 다라 동적 페이지링크를 뷰페이지에 전달
        return mv;
    }

    @GetMapping("project/insertproduceview")  // 작성클릭 후 페이지 리턴하는
    public String insertproduceview(){
        return "project/produceinsert";
    }

    @GetMapping("project/insertproduce")   // 작성 후 입력값 넘기는~ , 추가
    public String insertProduce(ProduceDto produceDto){
        projectService.insertProduce(produceDto);
        return "redirect:/project/produce";}

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
        return "redirect:/project/produce";
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
        /*projectService.updateProduce(dto);*/
        projectService.updateProduce(dto);
        return "redirect:/project/produce"; }
                    // 프로듀스 제작 완료 !!!!!!!!============================================
}











