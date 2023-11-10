package com.example.yoonlove.controller;

import com.example.yoonlove.dto.*;
import com.example.yoonlove.service.DropDownService;
import com.example.yoonlove.service.PagingService;
import com.example.yoonlove.service.ProjectService;
import com.example.yoonlove.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.text.DecimalFormat;
import java.util.List;
@Controller
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private PagingService pagingService;

    @Autowired
    private DropDownService dropDownService;
    @Autowired
    private UserService userService;

    //페이징처리를 위해 불러옴

    //프로젝트(기획)
    @GetMapping("project/project")   // 리스트목록!!!!!
    public ModelAndView selectListProject(PageDto pdto, @RequestParam(name="page", defaultValue = "1") int page,
                                          Principal user){
        ModelAndView mv = new ModelAndView();

        //유저정보 가저오는 dto
        UserDto userInfo = userService.getUser(user.getName());
        String companyId = userInfo.getCompany_id(); //회사 id 스트링

        PageDto pageDto = new PageDto("project", "project_id", page, pdto, companyId);
        PageDto pageInfo = pagingService.paging(pageDto);

        List<PageDto> pageList = pagingService.pageList(pageInfo.getPageStart(), pageInfo.getPageEnd(), page);
        String rink = pagingService.pageRink(pageDto);

        List<ProjectDto> dto = projectService.selectListProject(pageInfo);
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
    @ResponseBody
    public String insertProject(ProjectDto projectDto, Principal user){

        //유저정보 가저오는 dto
        UserDto userInfo = userService.getUser(user.getName());
        String companyId = userInfo.getCompany_id(); //회사 id 스트링

        projectDto.setCompany_id(companyId);
        projectDto.setProject_writer(userInfo.getNickname());
        projectService.insertProject(projectDto);
        return "/project/project";
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
    @ResponseBody
    public String updateProject(ProjectDto dto){
        projectService.updateProject(dto);
        return "/project/project";   // 목록페이지로 이동
    }
    // ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ프로젝트 끝

    //제작예산
    // httpserveltrequest -> @RequestParam -> Command Object (dto)
    @GetMapping("/project/budget/{project_id}")   // ddl 테이블명
        public ModelAndView selectListBudget(PageDto pdto, @RequestParam(name="page", defaultValue = "1") int page,
                                             Principal user, BudgetDto budgetDto){
        //유저정보 가저오는 dto
        UserDto userInfo = userService.getUser(user.getName());
        String companyId = userInfo.getCompany_id(); //회사 id 스트링

        PageDto pageDto = new PageDto("budget", "budget_id", page, pdto,companyId);
        pageDto.setPkid(budgetDto.getProject_id());

        PageDto pageInfo = pagingService.paging(pageDto);
        List<PageDto> pageList = pagingService.pageList(pageInfo.getPageStart(),pageInfo.getPageEnd(),page);
        String rink = pagingService.pageRink(pageDto);

        List<BudgetDto> dto = projectService.selectListBudget(pageInfo);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/project/listbudget");  // 머스터치 이름
        mv.addObject("selectListBudget", dto);
        mv.addObject("project_id",budgetDto.getProject_id());
        //예산 총계 구하는 메서드
        int totalInt;
        try {
            totalInt = projectService.getTotalBudget(pageDto);  //검색결과가 없을경우 null 에러 발생
        }catch (Exception e){
            totalInt = 0;
        }
        DecimalFormat decimalFormat = new DecimalFormat("#,###");  //숫자를 금액처럼 , 붙여줌
        String total = decimalFormat.format(totalInt);
        mv.addObject("total", total);

        //숫자로 된 amount를 3,000 금액으로 바꾸는 for문
        for(int i = 0; i<dto.size(); i++){
            String formattedAmount = decimalFormat.format(dto.get(i).getBudget_amount());
            dto.get(i).setFomattedAmount(formattedAmount);
        }

        mv.addObject("prefixUrl", "project");  //컨트롤러 이름
        mv.addObject("paging", pageInfo);  //페이징정보
        mv.addObject("pagelist", pageList); //페이지 하단부 페이지 리스트
        mv.addObject("pageRink",rink); //검색유무에 다라 동적 페이지링크를 뷰페이지에 전달
        return mv;
    }

    @GetMapping("/project/insertbudgetview")  // 작성클릭 후 페이지 리턴하는
    public ModelAndView insertbudgetview(Principal user)throws JsonProcessingException {
        UserDto userInfo = userService.getUser(user.getName());
        String companyId = userInfo.getCompany_id(); //회사 id 스트링

        String jsonListProject = dropDownService.dropDownOption("project",null, companyId);
        ModelAndView mv = new ModelAndView();
        mv.addObject("fkList", jsonListProject);
        mv.setViewName("project/budgetinsert");
        return mv;
    }

    @GetMapping("project/insertbudget")   // 작성 후 입력값 넘기는~
    @ResponseBody
    public String insertBudget(BudgetDto budgetDto){
        projectService.insertBudget(budgetDto);
        return "/project/budget/"+budgetDto.getProject_id();
    }

    @GetMapping("project/{budget_id}/selectbudget")
    public ModelAndView selectProject(BudgetDto budgetDto){
        BudgetDto dto = projectService.selectBudget(budgetDto);

        DecimalFormat decimalFormat = new DecimalFormat("#,###");  //숫자를 금액처럼 , 붙여줌
        String formattedAmount = decimalFormat.format(dto.getBudget_amount());
        dto.setFomattedAmount(formattedAmount);

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
    @ResponseBody
    public String updateBudget(BudgetDto dto){
        BudgetDto budgetDto = projectService.selectBudget(dto);
        projectService.updateBudget(dto);

        return "/project/budget/"+budgetDto.getProject_id();   // 목록페이지로 이동
    }
 // =====================budget 완료 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!


    @GetMapping("/project/produce/{project_id}")   //목록
    public ModelAndView selectListProduce(PageDto pdto, @RequestParam(name="page", defaultValue = "1")int page,
                                          Principal user, ProjectDto projectDto){
        //유저정보 가저오는 dto
        UserDto userInfo = userService.getUser(user.getName());
        String companyId = userInfo.getCompany_id(); //회사 id 스트링
        PageDto pageDto = new PageDto("produce", "pd_id", page,pdto, companyId);
        pageDto.setPkid(projectDto.getProject_id());
        PageDto pageInfo = pagingService.paging(pageDto);

        List<PageDto> pageList = pagingService.pageList(pageInfo.getPageStart(),pageInfo.getPageEnd(),page);
        String rink = pagingService.pageRink(pageDto);

        List<ProduceDto> dto = projectService.selectListProduce(pageInfo);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/project/listproduce");
        mv.addObject("selectListProduce", dto);
        mv.addObject("project_id",projectDto.getProject_id());
        mv.addObject("prefixUrl", "project"); //컨트롤러 이름
        mv.addObject("paging", pageInfo);  //페이징정보
        mv.addObject("pagelist", pageList); //페이지 하단부 페이지 리스트
        mv.addObject("pageRink",rink); //검색유무에 다라 동적 페이지링크를 뷰페이지에 전달
        return mv;
    }

    @GetMapping("project/insertproduceview/{project_id}")  // 작성클릭 후 페이지 리턴하는
    public ModelAndView insertproduceview(ProjectDto dto){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/project/produceinsert");
        mv.addObject("project_id",dto.getProject_id());
        return mv;
    }

    @GetMapping("project/insertproduce")   // 작성 후 입력값 넘기는~ , 추가
    @ResponseBody
    public String insertProduce(ProduceDto produceDto,Principal user){
        //유저정보 가저오는 dto
        UserDto userInfo = userService.getUser(user.getName());
        String companyId = userInfo.getCompany_id(); //회사 id 스트링
        produceDto.setCompany_id(companyId);
        projectService.insertProduce(produceDto);

        return "/project/produce/"+produceDto.getProject_id();
    }

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
        ProduceDto produceDto = projectService.selectProduce(dto);
        projectService.deleteProduce(dto);
        return "redirect:/project/produce/"+produceDto.getProject_id();
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
    @ResponseBody
    public String updateProduce(ProduceDto dto){
        projectService.updateProduce(dto);
        return "/project/produce"; }
// 프로듀스 제작 완료 !!!!!!!!============================================
}











