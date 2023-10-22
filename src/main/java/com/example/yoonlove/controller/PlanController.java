package com.example.yoonlove.controller;

import com.example.yoonlove.dto.*;
import com.example.yoonlove.service.PagingService;
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
    @Autowired
    PagingService pagingService;


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

    @GetMapping("plan/{time_id}/deleteTime") //삭제 처리
    public String deleteTime( ScheduleTimeDto dto) {
        planService.deleteTime(dto);
        return "redirect:/plan/scheduleTimeList";

    }

//============================================================================================촬영시간표


    @GetMapping("plan/actorManagementList")
    public ModelAndView selectListActorManagement(){

        List<ActorManagementDto> dto = planService.selectListActorManagement();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/plan/actorManagementList");
        mv.addObject("actorManagementList", dto);
        return mv;
    }


    @GetMapping("plan/actorManagement/{actor_id}")
    public ModelAndView selectActorManagement(ActorManagementDto dto) {
        ActorManagementDto actorManagementDetail = planService.selectActorManagement(dto);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("/plan/actorManagementDetail");
        mv.setStatus(HttpStatus.valueOf(200));
        mv.addObject("actorManagementDetail", actorManagementDetail);
        return mv;
    }

   @GetMapping("plan/insertactorManagementView")
    public ModelAndView insertactorManagementView() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("plan/insertactorManagementView");
        mv.setStatus(HttpStatus.valueOf(200));
        return mv;
    }


    @GetMapping("plan/insertactorManagement")  //컨텐츠 추가 처리
    public String insertActorManagement(ActorManagementDto dto) {

        planService.insertActorManagement(dto);

        return "redirect:/plan/actorManagementList";
    }


    @GetMapping("plan/{actor_id}/actorManagementUpdateView") //컨텐츠 업데이트하는 뷰
    public ModelAndView actorManagementUpdateView( ActorManagementDto dto) {
        ActorManagementDto actorManagementDto = planService.selectActorManagement(dto);//업데이트를 하려면 해당 컨텐츠 불러와야하니까 위에 selectContent메소드를 다시씀!
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/plan/actorManagementUpdateView");
        mv.setStatus(HttpStatus.valueOf(200));
        mv.addObject("actorManagementUpdate", actorManagementDto);
        return mv;
    }

    @GetMapping("plan/{actor_id}/updateActorManagement") //업데이트 처리
    public String updateActorManagement( ActorManagementDto dto) {
        planService.updateActorManagement(dto);
        return "redirect:/plan/actorManagementList";
    }

    @GetMapping("plan/{actor_id}/deleteActorManagement") //삭제 처리
    public String deleteActorManagement( ActorManagementDto dto) {
        planService.deleteActorManagement(dto);
        return "redirect:/plan/actorManagementList";

    }

//===========================================================================================출연진관라


    @GetMapping("plan/filmList")
    public ModelAndView selectListFilmPlan(){

        List<FilmPlanDto> dto = planService.selectListFilmPlan();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/plan/filmList");
        mv.addObject("filmList", dto);
        return mv;
    }


    @GetMapping("plan/filmPlan/{film_id}")
    public ModelAndView selectFilmPlan(FilmPlanDto dto) {
        FilmPlanDto filmPlanDetail = planService.selectFilmPlan(dto);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("/plan/filmPlanDetail");
        mv.setStatus(HttpStatus.valueOf(200));
        mv.addObject("filmPlanDetail", filmPlanDetail);
        return mv;
    }

    @GetMapping("plan/insertFilmPlanView")
    public ModelAndView insertFilmPlanView() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("plan/insertFilmPlanView");
        mv.setStatus(HttpStatus.valueOf(200));
        return mv;
    }


    @GetMapping("plan/insertFilm")  //컨텐츠 추가 처리
    public String insertFilm(FilmPlanDto dto) {

        planService.insertFilm(dto);

        return "redirect:/plan/filmList";
    }


    @GetMapping("plan/{film_id}/filmPlanUpdateView") //컨텐츠 업데이트하는 뷰
    public ModelAndView filmPlanUpdateView( FilmPlanDto dto) {
        FilmPlanDto filmPlanUpdate = planService.selectFilmPlan(dto);//업데이트를 하려면 해당 컨텐츠 불러와야하니까 위에 selectContent메소드를 다시씀!
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/plan/filmPlanUpdateView");
        mv.setStatus(HttpStatus.valueOf(200));
        mv.addObject("filmPlanUpdate", filmPlanUpdate);
        return mv;
    }

    @GetMapping("plan/{film_id}/updatefilm") //업데이트 처리
    public String updateFilm( FilmPlanDto dto) {
        planService.updateFilm(dto);
        return "redirect:/plan/filmList";
    }

    @GetMapping("plan/{film_id}/deleteFilm") //삭제 처리
    public String deleteFilm( FilmPlanDto dto) {
        planService.deleteFilm(dto);
        return "redirect:/plan/filmList";

    }


//=================================================================================

    @GetMapping("plan/scheduleMonthList")
    public ModelAndView selectListPlan(){

        List<ScheduleMonthDto> dto = planService.selectListPlan();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/plan/scheduleMonthList");
        mv.addObject("scheduleMonthList", dto);
        return mv;
    }


    @GetMapping("plan/scheduleMonth/{month_id}")
    public ModelAndView selectPlan(ScheduleMonthDto dto) {
        ScheduleMonthDto scheduleMonth = planService.selectPlan(dto);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("/plan/scheduleMonthDetail");
        mv.setStatus(HttpStatus.valueOf(200));
        mv.addObject("scheduleMonth", scheduleMonth);
        return mv;
    }

    @GetMapping("plan/insertScheduleMonthView")
    public ModelAndView insertScheduleMonthView() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("plan/insertScheduleMonthView");
        mv.setStatus(HttpStatus.valueOf(200));
        return mv;
    }


    @GetMapping("plan/insertPlan")  //컨텐츠 추가 처리
    public String insertPlan(ScheduleMonthDto dto) {

        planService.insertPlan(dto);

        return "redirect:/plan/scheduleMonthList";
    }


    @GetMapping("plan/{month_id}/ScheduleMonthUpdateView") //컨텐츠 업데이트하는 뷰
    public ModelAndView ScheduleMonthUpdateView( ScheduleMonthDto dto) {
        ScheduleMonthDto ScheduleMonthUpdate = planService.selectPlan(dto);//업데이트를 하려면 해당 컨텐츠 불러와야하니까 위에 selectContent메소드를 다시씀!
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/plan/ScheduleMonthUpdateView");
        mv.setStatus(HttpStatus.valueOf(200));
        mv.addObject("ScheduleMonthUpdate", ScheduleMonthUpdate);
        return mv;
    }

    @GetMapping("plan/{month_id}/updateScheduleMonth") //업데이트 처리
    public String  updatePlan( ScheduleMonthDto dto) {
        planService. updatePlan(dto);
        return "redirect:/plan/scheduleMonthList";
    }

    @GetMapping("plan/{month_id}/deleteScheduleMonth") //삭제 처리
    public String deletePlan( ScheduleMonthDto dto) {
        planService.deletePlan(dto);
        return "redirect:/plan/scheduleMonthList";

    }







}







