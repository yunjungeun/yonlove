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

        //회사 조인안하고 그냥 객체로 만듬
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

    @GetMapping("/admin/{user_id}/updateuserview")
    public ModelAndView updateUserView(UserDto userDto) {
        UserDto dto = adminService.selectUser(userDto);
        ModelAndView mv = new ModelAndView();

        //회사 조인안하고 그냥 객체로 만듬
        CompanyDto companyDto = new CompanyDto();
        companyDto.setCompany_id(dto.getCompany_id());
        CompanyDto companyname = adminService.selectCompany(companyDto);

        //부서 조인안하고 그냥 객체만듬
        DepartmentDto dptDto = new DepartmentDto();
        dptDto.setDpt_id(dto.getDpt_id());
        DepartmentDto dptname = adminService.selectDepartment(dptDto);

        mv.addObject("selectDpt",dptname);
        mv.addObject("selectCompany",companyname);

        mv.setViewName("/admin/userupdate");
        mv.addObject("selectUser", dto);
        return mv;
    }

    @GetMapping("/admin/{dpt_id}/deleteuser")
    public String deleteDepartment(UserDto dto){
        adminService.deleteUser(dto);
        return "redirect:/admin/user";
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
    @GetMapping("/admin/{user_id}/updateuser")
    public String updateUser(UserDto dto){
        System.out.println(dto.toString());
        adminService.updateUser(dto);
        return "redirect:/admin/user";
    }

    @GetMapping("/admin/{user}/deleteuser")
    public String deleteUser(UserDto dto){
        adminService.deleteUser(dto);
        return "redirect:/admin/user";
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
