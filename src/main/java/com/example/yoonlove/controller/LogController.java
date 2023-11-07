package com.example.yoonlove.controller;

import com.example.yoonlove.dto.LogDto;
import com.example.yoonlove.dto.PageDto;
import com.example.yoonlove.dto.UserDto;
import com.example.yoonlove.service.LogService;
import com.example.yoonlove.service.PagingService;
import com.example.yoonlove.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
public class LogController {

    @Autowired
    private LogService logService;
    @Autowired
    private PagingService pagingService;
    @Autowired
    private UserService userService;

    @GetMapping("log/log")
    public ModelAndView selectListLog(PageDto pdto, @RequestParam(name="page", defaultValue = "1") int page, Principal user){
        //유저정보 가저오는 dto
        UserDto userInfo = userService.getUser(user.getName());
        String companyId = userInfo.getCompany_id(); //회사 id 스트링

        PageDto pageDto = new PageDto("log","log_id", page,pdto, companyId);  // page???
        PageDto pageInfo = pagingService.paging(pageDto);


        // paging ==> 전체게시글 갯수 구해오는 메소드
        List<PageDto> pageList = pagingService.pageList(pageInfo.getPageStart(),pageInfo.getPageEnd(),page); // pageList==> 뷰페이지에 페이징 리스트를 생성해주는 리스트 메소드
        String rink = pagingService.pageRink(pageDto);

        List<LogDto> dto =  logService.selectListLog(pageInfo);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("log/log");

        mv.addObject("selectListLog" ,dto);

        mv.addObject("prefixUrl","log");
        mv.addObject("paging", pageInfo);  //페이징정보
        mv.addObject("pagelist", pageList); //페이지 하단부 페이지 리스트
        mv.addObject("pageRink",rink); //검색유무에 다라 동적 페이지링크를 뷰페이지에 전달
        return mv;
    }

    @GetMapping("log/selectlog/{log_id}")
    public ModelAndView selectLog(LogDto logdto){
        LogDto dto = logService.selectLog(logdto);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("log/logdetail");
        mv.addObject("selectLog", dto);
        return mv;
    }

    @GetMapping("log/insertLogView")
    public  ModelAndView insertLogView(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("log/logInsertView");
        return mv;
    }

    @GetMapping("log/insertLog")
    @ResponseBody
    public String insertLog(LogDto dto) {

        System.out.println("test111!!!!!"+dto.toString());

        logService.insertLog(dto);

        System.out.println("test222!!!!!"+dto.toString());

        return "/log/log";
    }

    @GetMapping("log/{log_id}/logUpdateView")
    public ModelAndView selectBbsUpdateView(LogDto dto) {
        LogDto logdto = logService.selectLog(dto);//업데이트를 하려면 해당 컨텐츠 불러와야하니까 위에 selectContent메소드를 다시씀!
        ModelAndView mv = new ModelAndView();
        mv.setViewName("log/logUpdateView");
        mv.addObject("updateLog", logdto);
        return mv;
    }

    @GetMapping("log/{log_id}/updateLog")
    @ResponseBody
    public String updateLog(LogDto dto){
        logService.updateLog(dto);
        return "/log/log";
    }

    @GetMapping("log/{log_id}/deleteLog")
    public String deleteLog(LogDto dto) {
        logService.deleteLog(dto);
        return "redirect:/log/log";

    }

}