package com.example.yoonlove.controller;

import com.example.yoonlove.dto.CompanyDto;
import com.example.yoonlove.dto.DepartmentDto;
import com.example.yoonlove.dto.PageDto;
import com.example.yoonlove.dto.UserDto;
import com.example.yoonlove.service.AdminService;
import com.example.yoonlove.service.PagingService;
import com.example.yoonlove.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private PagingService pagingService;
    @Autowired
    private UserService userService;


    //유저관리
    @GetMapping("/admin/user")
    public ModelAndView selectListUser(PageDto pdto,@RequestParam(name="page", defaultValue = "1") int page){
        PageDto pageDto = new PageDto("users","user_id",page,pdto);
        PageDto pageInfo = pagingService.paging(pageDto);

        List<PageDto> pagelist = pagingService.pageList(pageInfo.getPageStart(),pageInfo.getPageEnd(),page);
        String rink = pagingService.pageRink(pageDto);

        System.out.println(pageInfo.toString());
        List<UserDto> pagedto = adminService.selectListUser(pageInfo);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/admin/user");
        mv.addObject("selectListUser", pagedto);


        //페이징에 필요한센션
        mv.addObject("prefixUrl", "admin");
        mv.addObject("paging", pageInfo);  //페이징정보
        mv.addObject("pageList", pagelist); //페이지 하단부 페이지 리스트
        mv.addObject("pageRink",rink); //검색유무에 다라 동적 페이지링크를 뷰페이지에 전달
        return mv;
    }

    @GetMapping("/admin/{user_id}/selectuser")
    public ModelAndView selectUser(UserDto userDto){
        UserDto dto = adminService.selectUser(userDto);
        ModelAndView mv = new ModelAndView();


        CompanyDto companyDto = new CompanyDto();
        companyDto.setCompany_id(dto.getCompany_id());
        CompanyDto companyname = adminService.selectCompany(companyDto);

        //부서 조인안하고 그냥 객체만듬
        DepartmentDto dptDto = new DepartmentDto();
        dptDto.setDpt_id(dto.getDpt_id());
        DepartmentDto dptname = adminService.selectDepartment(dptDto);

        mv.addObject("selectDpt",dptname);
        mv.addObject("selectCompany",companyname);
        mv.setViewName("/admin/userselect");
        mv.addObject("selectUser", dto);
        return mv;
    }

    @GetMapping("/admin/insertuser")
    public ModelAndView insertUser(){
        //실행할 메소드(서비스 부분에 있는 메소드)
        adminService.insertUser();

        //세션 객체생셩
        ModelAndView mv = new ModelAndView();
        //보여줄 view페이지 이름(ooo.mustache)
        mv.setViewName("/admin/insertuser");
        return mv;
    }
    @GetMapping("/admin/updateuser")
    public ModelAndView updateUser(){
        //실행할 메소드(서비스 부분에 있는 메소드)
        adminService.updateUser();

        //세션 객체생셩
        ModelAndView mv = new ModelAndView();
        //보여줄 view페이지 이름(ooo.mustache)
        mv.setViewName("/admin/updateuser");
        return mv;
    }
    @GetMapping("/admin/deleteuser")
    public ModelAndView deleteUser(){
        //실행할 메소드(서비스 부분에 있는 메소드)
        adminService.deleteUser();

        //세션 객체생셩
        ModelAndView mv = new ModelAndView();
        //보여줄 view페이지 이름(ooo.mustache)
        mv.setViewName("/admin/deleteuser");
        return mv;
    }

    //부서관리
    @GetMapping("/admin/department")
    public ModelAndView selectListDepartment(PageDto pdto, @RequestParam(name="page", defaultValue = "1") int page){
        PageDto pageDto = new PageDto("department","dpt_id",page,pdto);

        PageDto pageInfo = pagingService.paging(pageDto);
        List<PageDto> pageList = pagingService.pageList(pageInfo.getPageStart(),pageInfo.getPageEnd(),page);
        String rink = pagingService.pageRink(pageDto);

        List<DepartmentDto> dto = adminService.selectListDepartment(pageInfo);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/admin/dpt");
        mv.addObject("selectListDpt", dto);

        mv.addObject("prefixUrl", "admin");
        mv.addObject("paging", pageInfo);  //페이징정보
        mv.addObject("pagelist", pageList); //페이지 하단부 페이지 리스트
        mv.addObject("pageRink",rink); //검색유무에 다라 동적 페이지링크를 뷰페이지에 전달

        return mv;
    }

    @GetMapping("/admin/{dpt_id}/selectdpt")
    public ModelAndView selectDepartment(DepartmentDto departmentDto){
        DepartmentDto dto = adminService.selectDepartment(departmentDto);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/admin/dptselect");
        mv.addObject("selectDpt", dto);
        return mv;
    }

    @GetMapping("admin/insertdptview")
    public String insertDptView(){
        return "admin/dptinsert";
    }
    @GetMapping("/admin/insertdpt")
    public String insertDepartment(DepartmentDto dto){
        adminService.insertDepartment(dto);
        return "redirect:/admin/department";
    }

    @GetMapping("/admin/{dpt_id}/updatedptview")
    public ModelAndView updatedptview(DepartmentDto departmentDto){
        DepartmentDto dto = adminService.selectDepartment(departmentDto);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("admin/dptupdate");
        mv.addObject("selectDpt", dto);
        return mv;
    }

    @GetMapping("/admin/{dpt_id}/updatedpt")
    public String updateDepartment(DepartmentDto dto){
        adminService.updateDepartment(dto);
        return "redirect:/admin/department";
    }
    @GetMapping("/admin/{dpt_id}/deletedpt")
    public String deleteDepartment(DepartmentDto dto){
        adminService.deleteDepartment(dto);
        return "redirect:/admin/department";
    }
}
