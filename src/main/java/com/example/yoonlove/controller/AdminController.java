package com.example.yoonlove.controller;

import com.example.yoonlove.dto.DepartmentDto;
import com.example.yoonlove.dto.UserDto;
import com.example.yoonlove.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    //유저관리
    @GetMapping("/admin/listuser")
    public ModelAndView selectListUser(){
        //실행할 메소드(서비스 부분에 있는 메소드)
        List<UserDto> dto = adminService.selectListUser();

        //세션 객체생셩
        ModelAndView mv = new ModelAndView();
        //보여줄 view페이지 이름(ooo.mustache)
        mv.setViewName("/admin/listuser");

        //dto객체 형태로 "selectListCreator"이라는 이름으로 세션형성
        mv.addObject("selectListUser", dto);
        return mv;
    }

    @GetMapping("/admin/selectuser")
    public ModelAndView selectUser(){
        //실행할 메소드(서비스 부분에 있는 메소드)
        UserDto dto = adminService.selectUser();

        //세션 객체생셩
        ModelAndView mv = new ModelAndView();
        //보여줄 view페이지 이름(ooo.mustache)
        mv.setViewName("/admin/selectuser");

        //dto객체 형태로 "selectListCreator"이라는 이름으로 세션형성
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
    @GetMapping("/admin/dpt")
    public ModelAndView selectListDepartment(){
        List<DepartmentDto> dto = adminService.selectListDepartment();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/admin/dpt");
        mv.addObject("selectListDpt", dto);
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
        return "redirect:/admin/dpt";
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
        return "redirect:/admin/dpt";
    }
    @GetMapping("/admin/{dpt_id}/deletedpt")
    public String deleteDepartment(DepartmentDto dto){
        adminService.deleteDepartment(dto);
        return "redirect:/admin/dpt";
    }
}
