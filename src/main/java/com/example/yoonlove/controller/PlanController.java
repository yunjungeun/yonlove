package com.example.yoonlove.controller;

import com.example.yoonlove.dto.ScheduleDayDto;
import com.example.yoonlove.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;



@Controller
public class PlanController {

    @Autowired
    private PlanService planService;


    @GetMapping("plan/scheduleList")
    public ModelAndView selectListSchedule() {
        List<ScheduleDayDto> dto = planService.selectListSchedule();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/plan/scheduleList");
        mv.addObject("scheduleList", dto);
        return mv;
    }


    @GetMapping("plan/schedule/{day_id}")
    public ModelAndView selectSchedule(ScheduleDayDto dto) {
        ScheduleDayDto scheduleDetail = planService.selectSchedule(dto);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("/plan/scheduleDetail");
        mv.setStatus(HttpStatus.valueOf(200));
        mv.addObject("scheduleDetail", scheduleDetail);
        return mv;
    }

    @GetMapping("plan/insertScheduleView")
    public ModelAndView insertScheduleView() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("plan/insertSchedule");
        mv.setStatus(HttpStatus.valueOf(200));
        return mv;
    }


    @GetMapping("plan/insertSchedule")  //컨텐츠 추가 처리
    public String insertSchedule(ScheduleDayDto dto) {
        planService.insertSchedule(dto);

        return "redirect:/plan/scheduleList";
    }
}