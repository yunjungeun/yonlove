package com.example.yoonlove.controller;

import com.example.yoonlove.dto.ScheduleDayDto;
import com.example.yoonlove.dto.ScheduleTimeDto;
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

    @GetMapping("plan/{day_id}/scheduleUpdateView") //컨텐츠 업데이트하는 뷰
    public ModelAndView scheduleUpdateView( ScheduleDayDto dto) {
        ScheduleDayDto scheduleDayDto = planService.selectSchedule(dto);//업데이트를 하려면 해당 컨텐츠 불러와야하니까 위에 selectContent메소드를 다시씀!
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/plan/updateSchedule");
        mv.setStatus(HttpStatus.valueOf(200));
        mv.addObject("updateSchedule", scheduleDayDto);
        return mv;
    }

    @GetMapping("plan/{day_id}/updateSchedule") //업데이트 처리
    public String updateSchedule( ScheduleDayDto dto) {
        planService.updateSchedule(dto);
        return "redirect:/plan/scheduleList";
    }

    @GetMapping("plan/{day_id}/deleteSchedule") //삭제 처리
    public String deleteSchedule( ScheduleDayDto dto) {
        planService.deleteSchedule(dto);
        return "redirect:/plan/scheduleList";

    }
//===================================================================================================================================== 촬영계획표


    @GetMapping("plan/scheduleTimeList")
    public ModelAndView selectListScheduleTime(){
        List<ScheduleTimeDto> dto = planService.selectListScheduleTime();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/plan/scheduleTimeList");
        mv.addObject("scheduleTimeList", dto);
        return mv;
    }


    @GetMapping("plan/scheduleTime/{time_id}")
    public ModelAndView selectScheduleTime(ScheduleTimeDto dto) {
        ScheduleTimeDto scheduleDetail = planService.selectScheduleTime(dto);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("/plan/scheduleTime");
        mv.setStatus(HttpStatus.valueOf(200));
        mv.addObject("scheduleTime", scheduleDetail);
        return mv;
    }

    @GetMapping("plan/insertScheduleTimeView")
    public ModelAndView insertScheduleTimeView() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("plan/insertScheduleTime");
        mv.setStatus(HttpStatus.valueOf(200));
        return mv;
    }


    @GetMapping("plan/insertScheduleTime")  //컨텐츠 추가 처리
    public String insertTime(ScheduleTimeDto dto) {

        planService.insertTime(dto);

        return "redirect:/plan/scheduleTimeList";
    }


    @GetMapping("plan/{time_id}/scheduleTimeUpdateView") //컨텐츠 업데이트하는 뷰
    public ModelAndView scheduleTimeUpdateView( ScheduleTimeDto dto) {
        ScheduleTimeDto scheduleTimeDto = planService.selectScheduleTime(dto);//업데이트를 하려면 해당 컨텐츠 불러와야하니까 위에 selectContent메소드를 다시씀!
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/plan/updateScheduleTime");
        mv.setStatus(HttpStatus.valueOf(200));
        mv.addObject("updateScheduleTime", scheduleTimeDto);
        return mv;
    }

    @GetMapping("plan/{time_id}/updateScheduleTime") //업데이트 처리
    public String updateTime( ScheduleTimeDto dto) {
        planService.updateTime(dto);
        return "redirect:/plan/scheduleTimeList";
    }

    @GetMapping("plan/{time_id}/delete") //삭제 처리
    public String deleteTime( ScheduleTimeDto dto) {
        planService.deleteTime(dto);
        return "redirect:/plan/scheduleTimeList";

    }






}







