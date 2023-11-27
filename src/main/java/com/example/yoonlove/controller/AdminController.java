package com.example.yoonlove.controller;

import com.example.yoonlove.dto.CompanyDto;
import com.example.yoonlove.dto.DepartmentDto;
import com.example.yoonlove.dto.PageDto;
import com.example.yoonlove.dto.UserDto;
import com.example.yoonlove.service.AdminService;
import com.example.yoonlove.service.DropDownService;
import com.example.yoonlove.service.PagingService;
import com.example.yoonlove.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private PagingService pagingService;
    @Autowired
    private UserService userService;
    @Autowired
    private DropDownService dropDownService;

    //유저관리
    @GetMapping("/admin/user")
    public ModelAndView selectListUser(PageDto pdto,@RequestParam(name="page", defaultValue = "1") int page, Principal user){
        //유저정보 가저오는 dto
        UserDto userInfo = userService.getUser(user.getName());
        String companyId = userInfo.getCompany_id(); //회사 id 스트링

        PageDto pageDto = new PageDto("users","user_id",page,pdto,companyId);
        PageDto pageInfo = pagingService.paging(pageDto);

        List<PageDto> pagelist = pagingService.pageList(pageInfo.getPageStart(),pageInfo.getPageEnd(),page);
        String rink = pagingService.pageRink(pageDto);

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

    @GetMapping("/mypage")
    public ModelAndView mypage(Principal user){
        UserDto userDto = new UserDto();
        userDto.setUser_id(user.getName());
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
    public ModelAndView updateUserView(UserDto userDto) throws JsonProcessingException {
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

        HashMap<String, Boolean> authority = adminService.authorityCheck(dto);
        mv.addObject("authority",authority);

        //-------------------------------아래 내용 추가 및 수정함
        Map<String, Object> model = new HashMap<>();
        model.put("selectDpt", dptname);
        model.put("selectCompany", companyname);
        model.put("selectUser", dto);
        model.put("isAdmin", "admin".equals(dto.getAuthority()));
        model.put("isNormal", "일반".equals(dto.getAuthority()));

        String jsonList = dropDownService.dropDownOption("department",null, dto.getCompany_id());

        mv.addObject("fkList", jsonList);
        mv.setViewName("/admin/userupdate");
        mv.addAllObjects(model);

        return mv;
    }

    @PostMapping("/admin/{user_id}/updateuser")
    @ResponseBody
    public String updateUser(UserDto dto) {
        adminService.updateUser(dto);

        return "/admin/user";
    }


    @PostMapping("/admin/{user_id}/deleteuser")
    public String deleteUser(UserDto dto){
        adminService.deleteUser(dto);
        return "redirect:/admin/user";
    }


    //부서관리
    @GetMapping("/admin/department")
    public ModelAndView selectListDepartment(PageDto pdto, @RequestParam(name="page", defaultValue = "1") int page,
                                             Principal user){
        //유저정보 가저오는 dto
        UserDto userInfo = userService.getUser(user.getName());
        //유저의 회사정보를 가저오는 코드
        String company = userInfo.getCompany_id(); //회사 id 스트링

        PageDto pageDto = new PageDto("department","dpt_id",page,pdto, company);

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



    @GetMapping("/admin/adduserview")
    public ModelAndView adduser(){

        ModelAndView mv = new ModelAndView();
        mv.setViewName("/admin/adduser");
        return mv;

    }

    @PostMapping("/admin/adduser")
    @ResponseBody
    public String updateContent(Principal user, UserDto userDto) {
      String userId = user.getName();

      UserDto myUser = userService.getUser(userId);
      userDto.setCompany_id(myUser.getCompany_id());
      userService.updateUser(userDto);

      return "/admin/user";
    }

    @GetMapping("/admin/{dpt_id}/selectdpt")
    public ModelAndView selectDepartment(DepartmentDto departmentDto, PageDto pdto,@RequestParam(name="page", defaultValue = "1") int page,
                                         Principal user){
        //유저정보 가저오는 dto
        UserDto userInfo = userService.getUser(user.getName());
        String companyId = userInfo.getCompany_id(); //회사 id 스트링

        DepartmentDto dto = adminService.selectDepartment(departmentDto);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/admin/dptselect");

        //서브게시판
        pdto.setPkid(dto.getDpt_id());
        PageDto pageDto = new PageDto("users","user_id",page,pdto,companyId);

        PageDto pageInfo = pagingService.paging(pageDto);

        List<PageDto> pageList = pagingService.pageList(pageInfo.getPageStart(),pageInfo.getPageEnd(),page);
        String rink = pagingService.subPageRink(pageDto,"department");

        List<UserDto> subList = adminService.selectListUser(pageInfo);
        mv.addObject("selectListUser", subList);

        //페이징에 필요한센션
        mv.addObject("pageDto", pageDto);
        mv.addObject("prefixUrl", "scenario"); //컨트롤러 이름
        mv.addObject("paging", pageInfo);  //페이징정보
        mv.addObject("pagelist", pageList); //페이지 하단부 페이지 리스트
        mv.addObject("pageRink",rink); //검색유무에 다라 동적 페이지링크를 뷰페이지에 전달
        //서브게시판

        mv.addObject("selectDpt", dto);

        return mv;
    }

    @GetMapping("admin/insertdptview")
    public String insertDptView(){
        return "admin/dptinsert";
    }


    @PostMapping("/admin/insertdpt")
    @ResponseBody
    public String insertDepartment(DepartmentDto dto, Principal user){
        //로그인한 유저의 dto정보를 가져옴
        UserDto userInfo = userService.getUser(user.getName());
        // 유저 정보에서 company_id를 추춣하여 부서dto에 저장함
        dto.setCompany_id(userInfo.getCompany_id());
        adminService.insertDepartment(dto);
        return "/admin/department";
    }

    @GetMapping("/admin/{dpt_id}/updatedptview")
    public ModelAndView updatedptview(DepartmentDto departmentDto){
        DepartmentDto dto = adminService.selectDepartment(departmentDto);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("admin/dptupdate");
        mv.addObject("selectDpt", dto);
        return mv;
    }

    @PostMapping("/admin/{dpt_id}/updatedpt")
    @ResponseBody
    public String updateDepartment(DepartmentDto dto){
        adminService.updateDepartment(dto);
        return "/admin/department";
    }

    @PostMapping("/admin/{dpt_id}/deletedpt")
    public String deleteDepartment(DepartmentDto dto){
        adminService.deleteDepartment(dto);
        return "redirect:/admin/department";
    }
}
