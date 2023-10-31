package com.example.yoonlove.controller;

import com.example.yoonlove.dto.*;
import com.example.yoonlove.service.DropDownService;
import com.example.yoonlove.service.PagingService;
import com.example.yoonlove.service.PlanService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;



@Controller
public class PlanController {

    @Autowired
    private PlanService planService;
    @Autowired
    private PagingService pagingService;
    @Autowired
    private DropDownService dropDownService;


    @GetMapping("/plan/schedule_day")
    public ModelAndView selectListSchedule(PageDto pdto, @RequestParam(name="page", defaultValue = "1") int page) {

        PageDto pageDto = new PageDto("schedule_day","day_id", page,pdto);
        PageDto pageInfo = pagingService.paging(pageDto); // paging ==> 전체게시글 갯수 구해오는 메소드
        List<PageDto> pageList = pagingService.pageList(pageInfo.getPageStart(),pageInfo.getPageEnd(),page); // pageList==> 뷰페이지에 페이징 리스트를 생성해주는 리스트 메소드
        String rink = pagingService.pageRink(pageDto);


        List<ScheduleDayDto> dto = planService.selectListSchedule(pageInfo);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/plan/schedule_day");
        mv.addObject("scheduleList", dto);

        mv.addObject("prefixUrl","plan");
        mv.addObject("paging", pageInfo);  //페이징정보
        mv.addObject("pagelist", pageList); //페이지 하단부 페이지 리스트
        mv.addObject("pageRink",rink); //검색유무에 다라 동적 페이지링크를 뷰페이지에 전달
        return mv;
    }

    @GetMapping("plan/schedule/{day_id}")
    public ModelAndView selectSchedule(ScheduleDayDto dto) {
        ModelAndView mv = new ModelAndView();

        //테이블1 start : film_plan 조인 리스트
        List<FilmPlanDto> dayTable1 = planService.selectListDayTable1(dto.getDay_id());
        mv.addObject("table1",dayTable1);
        //테이블1 end

        //테이블2 start : schedule_time 조인 리스트
        List<ScheduleTimeDto> dayTable2 = planService.selectListDayTable2(dto.getDay_id());
        mv.addObject("table2",dayTable2);
        //테이블2 end

        //테이블3 start : actor_management : 출연자관리
        List<ActorManagementDto> dayTable3 = planService.selectListDayTable3(dto.getDay_id());
        mv.addObject("table3",dayTable3);
        //테이블3 end

        //일일촬영계획 코드  // 수정엄금
        ScheduleDayDto scheduleDetail = planService.selectSchedule(dto);
        mv.setViewName("/plan/scheduleDetail");
        mv.addObject("scheduleDetail", scheduleDetail);
        //일일촬영계획 end
        return mv;
    }

    @GetMapping("plan/insertScheduleView")
    public ModelAndView insertScheduleView() throws JsonProcessingException{
        String jsonList = dropDownService.dropDownOption("project",null);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("/plan/insertSchedule");
        mv.addObject("fkList", jsonList);
        return mv;
    }


    @GetMapping("plan/insertSchedule")  //컨텐츠 추가 처리
    public String insertSchedule(ScheduleDayDto dto) {
        planService.insertSchedule(dto);

        return "redirect:/plan/schedule_day";
    }

    @GetMapping("plan/{day_id}/scheduleUpdateView") //컨텐츠 업데이트하는 뷰
    public ModelAndView scheduleUpdateView(ScheduleDayDto dto) {

        ScheduleDayDto scheduleDayDto = planService.selectSchedule(dto);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/plan/updateSchedule");
        mv.setStatus(HttpStatus.valueOf(200));
        mv.addObject("updateSchedule", scheduleDayDto);
        return mv;
    }

    @GetMapping("plan/{day_id}/updateSchedule") //업데이트 처리
    public String updateSchedule( ScheduleDayDto dto) {
        planService.updateSchedule(dto);
        return "redirect:/plan/schedule_day";
    }

    @GetMapping("plan/{day_id}/deleteSchedule") //삭제 처리
    public String deleteSchedule( ScheduleDayDto dto) {
        planService.deleteSchedule(dto);
        return "redirect:/plan/schedule_day";

    }
//===================================================================================================================================== 촬영계획표


    @GetMapping("plan/schedule_time")
    public ModelAndView selectListScheduleTime(PageDto pdto, @RequestParam(name="page", defaultValue = "1") int page){
        PageDto pageDto = new PageDto("schedule_time","time_id", page,pdto);  // page???
        PageDto pageInfo = pagingService.paging(pageDto);

        // paging ==> 전체게시글 갯수 구해오는 메소드
        List<PageDto> pageList = pagingService.pageList(pageInfo.getPageStart(),pageInfo.getPageEnd(),page); // pageList==> 뷰페이지에 페이징 리스트를 생성해주는 리스트 메소드
        String rink = pagingService.pageRink(pageDto);

        List<ScheduleTimeDto> dto = planService.selectListScheduleTime(pageInfo);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("/plan/scheduleTimeList");
        mv.addObject("scheduleTimeList", dto);

        mv.addObject("prefixUrl","plan");
        mv.addObject("paging", pageInfo);  //페이징정보
        mv.addObject("pagelist", pageList); //페이지 하단부 페이지 리스트
        mv.addObject("pageRink",rink); //검색유무에 다라 동적 페이지링크를 뷰페이지에 전달

        return mv;
    }


    @GetMapping("plan/scheduleTime/{time_id}")
    public ModelAndView selectScheduleTime(ScheduleTimeDto dto) {
        ScheduleTimeDto scheduleDetail = planService.selectScheduleTime(dto);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("/plan/scheduleTime");
        mv.addObject("scheduleTime", scheduleDetail);
        return mv;
    }

    @GetMapping("plan/insertScheduleTimeView")
    public ModelAndView insertScheduleTimeView() throws JsonProcessingException {
        String jsonList = dropDownService.dropDownOption("project",null);

        ModelAndView mv = new ModelAndView();
        mv.addObject("fkList", jsonList);
        mv.setViewName("/scene/sceneinsert");
        mv.setViewName("plan/insertScheduleTime");
        return mv;
    }


    @GetMapping("plan/insertScheduleTime")  //컨텐츠 추가 처리
    public String insertTime(ScheduleTimeDto dto) {
        System.out.println(dto.toString());
        planService.insertTime(dto);

        return "redirect:/plan/schedule_time";
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
        return "redirect:/plan/schedule_time";
    }

    @GetMapping("plan/{time_id}/deleteTime") //삭제 처리
    public String deleteTime( ScheduleTimeDto dto) {
        planService.deleteTime(dto);
        return "redirect:/plan/schedule_time";

    }

//============================================================================================촬영시간표


    @GetMapping("/plan/actor_management")
    public ModelAndView selectListActorManagement(PageDto pdto, @RequestParam(name="page", defaultValue = "1") int page){
        PageDto pageDto = new PageDto("actor_management","actor_id", page,pdto);
        PageDto pageInfo = pagingService.paging(pageDto);

        // paging ==> 전체게시글 갯수 구해오는 메소드
        List<PageDto> pageList = pagingService.pageList(pageInfo.getPageStart(),pageInfo.getPageEnd(),page); // pageList==> 뷰페이지에 페이징 리스트를 생성해주는 리스트 메소드
        String rink = pagingService.pageRink(pageDto);

        List<ActorManagementDto> dto = planService.selectListActorManagement(pageInfo);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/plan/actorManagementList");
        mv.addObject("actorManagementList", dto);

        mv.addObject("prefixUrl","plan");
        mv.addObject("paging", pageInfo);  //페이징정보
        mv.addObject("pagelist", pageList); //페이지 하단부 페이지 리스트
        mv.addObject("pageRink",rink); //검색유무에 다라 동적 페이지링크를 뷰페이지에 전달

        return mv;
    }


    @GetMapping("plan/actorManagement/{actor_id}")
    public ModelAndView selectActorManagement(ActorManagementDto dto) {
        ActorManagementDto actorManagementDetail = planService.selectActorManagement(dto);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("/plan/actorManagementDetail");
        mv.addObject("actorManagementDetail", actorManagementDetail);
        return mv;
    }

   @GetMapping("plan/insertactorManagementView")
    public ModelAndView insertactorManagementView() throws JsonProcessingException{
       //actorManagement 작성 옵션값 만들기 옵션은 시니라오에 있음.
        //fk값으로 db검색결과
       List<ScheduleDayDto> scheduleDayDto = planService.selectFk();

       //검색리스트를 json 리스트 문자열로 생성
       String jsonList = planService.fkJson(scheduleDayDto);

        ModelAndView mv = new ModelAndView();
        mv.addObject("fkList", jsonList);
        mv.setViewName("plan/insertactorManagementView");
        return mv;
    }


    @GetMapping("plan/insertactorManagement")  //컨텐츠 추가 처리
    public String insertActorManagement(ActorManagementDto dto) {
        planService.insertActorManagement(dto);
        return "redirect:/plan/actor_management";
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
        return "redirect:/plan/actor_management";
    }

    @GetMapping("plan/{actor_id}/deleteActorManagement") //삭제 처리
    public String deleteActorManagement( ActorManagementDto dto) {
        planService.deleteActorManagement(dto);
        return "redirect:/plan/actor_management";
    }

//===========================================================================================출연자 관리 end

    @GetMapping("plan/film_plan")
    public ModelAndView selectListFilmPlan(PageDto pdto, @RequestParam(name="page", defaultValue = "1") int page){

        PageDto pageDto = new PageDto("film_plan","film_id", page,pdto);

        PageDto pageInfo = pagingService.paging(pageDto);
        System.out.println(pageDto.getTotalPost());

        // paging ==> 전체게시글 갯수 구해오는 메소드
        List<PageDto> pageList = pagingService.pageList(pageInfo.getPageStart(),pageInfo.getPageEnd(),page); // pageList==> 뷰페이지에 페이징 리스트를 생성해주는 리스트 메소드
        String rink = pagingService.pageRink(pageDto);



        List<FilmPlanDto> dto = planService.selectListFilmPlan(pageInfo);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/plan/filmList");
        mv.addObject("filmList", dto);

        mv.addObject("prefixUrl","plan");
        mv.addObject("paging", pageInfo);  //페이징정보
        mv.addObject("pagelist", pageList); //페이지 하단부 페이지 리스트
        mv.addObject("pageRink",rink); //검색유무에 다라 동적 페이지링크를 뷰페이지에 전달


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
    public ModelAndView insertFilmPlanView(ScheduleDayDto dto) throws JsonProcessingException{
        ModelAndView mv = new ModelAndView();

        String jsonList = dropDownService.dropDownOption("project",null);
        mv.addObject("fkList", jsonList);
        mv.addObject("dayId", dto.getDay_id());
        mv.setViewName("plan/insertFilmPlanView");
        return mv;
    }


    @GetMapping("plan/insertFilm")  //컨텐츠 추가 처리
    public String insertFilm(FilmPlanDto dto) {
        planService.insertFilm(dto);

        return "redirect:/plan/film_plan";
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
        return "redirect:/plan/film_plan";
    }

    @GetMapping("plan/{film_id}/deleteFilm") //삭제 처리
    public String deleteFilm( FilmPlanDto dto) {
        planService.deleteFilm(dto);
        return "redirect:/plan/film_plan";

    }


//=================================================================================

    @GetMapping("plan/schedule_month")
    public ModelAndView selectListPlan(PageDto pdto, @RequestParam(name="page", defaultValue = "1") int page){
        PageDto pageDto = new PageDto("schedule_month","month_id", page,pdto);
        PageDto pageInfo = pagingService.paging(pageDto);

        // paging ==> 전체게시글 갯수 구해오는 메소드
        List<PageDto> pageList = pagingService.pageList(pageInfo.getPageStart(),pageInfo.getPageEnd(),page); // pageList==> 뷰페이지에 페이징 리스트를 생성해주는 리스트 메소드
        String rink = pagingService.pageRink(pageDto);

        List<ScheduleMonthDto> dto = planService.selectListPlan(pageInfo);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/plan/scheduleMonthList");
        mv.addObject("scheduleMonthList", dto);

        mv.addObject("prefixUrl","plan");
        mv.addObject("paging", pageInfo);  //페이징정보
        mv.addObject("pagelist", pageList); //페이지 하단부 페이지 리스트
        mv.addObject("pageRink",rink); //검색유무에 다라 동적 페이지링크를 뷰페이지에 전달

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

        return "redirect:/plan/schedule_month";
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
        return "redirect:/plan/schedule_month";
    }

    @GetMapping("plan/{month_id}/deleteScheduleMonth") //삭제 처리
    public String deletePlan( ScheduleMonthDto dto) {
        planService.deletePlan(dto);
        return "redirect:/plan/schedule_month";

    }

}
