package com.example.yoonlove.controller;

import com.example.yoonlove.dto.LogDto;
import com.example.yoonlove.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class LogController {

    @Autowired
    private LogService logService;

    @GetMapping("log/log")
    public ModelAndView selectListLog(){
        List<LogDto> dto =  logService.selectListLog();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("log/listlog");
        mv.addObject("selectListLog" ,dto);
        return mv;
    }

    @GetMapping("log/selectlog/{log_id}")
    public ModelAndView selectLog(LogDto logdto){
        LogDto dto = logService.selectLog(logdto);
        System.out.println(dto.toString());
        ModelAndView mv = new ModelAndView();
        mv.setViewName("log/logdetail");
        mv.addObject("selectLog", dto);
        return mv;
    }

    @GetMapping("log/insertLogView")
    public  ModelAndView insertLogView(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("log/logInsertView");

        mv.setStatus(HttpStatus.valueOf(200));
        return mv;
    }

    @GetMapping("log/insertLog")
    public String insertLog(LogDto dto) {
        System.out.println(dto.toString());  // sql 들어가기 직전에 뭐가 들어가있는지 확인하는 용도..!!
        logService.insertLog(dto);
        return "redirect:/log/log";
    }



    @GetMapping("log/{log_id}/logUpdateView")
    public ModelAndView selectBbsUpdateView(LogDto dto) {
        LogDto logdto = logService.selectLog(dto);//업데이트를 하려면 해당 컨텐츠 불러와야하니까 위에 selectContent메소드를 다시씀!
        ModelAndView mv = new ModelAndView();
        mv.setViewName("log/logUpdateView");
        mv.setStatus(HttpStatus.valueOf(200));
        mv.addObject("updateLog", logdto);
        return mv;
    }

    @GetMapping("log/{log_id}/updateLog")
    public String updateLog(LogDto dto){
        logService.updateLog(dto);
        return "redirect:/log/log";
    }

    @GetMapping("log/{log_id}/deleteLog")
    public String deleteLog(LogDto dto) {
        logService.deleteLog(dto);
        return "redirect:/log/log";

    }

}