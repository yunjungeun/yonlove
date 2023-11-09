package com.example.yoonlove.mapper;

import com.example.yoonlove.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlanMapper {

    public List<ScheduleDayDto> selectListSchedule(PageDto pageInfo);

    public PageDto totalSceduledayPost(PageDto dto);

    public List<ProjectDto> selectFkDay();

    public ScheduleDayDto selectSchedule(ScheduleDayDto dto);

    public void insertSchedule(ScheduleDayDto dto);

    public void updateSchedule(ScheduleDayDto dto);

    public void deleteSchedule(ScheduleDayDto dto);

    //월력형 테이블에 들어갈 메소드
    List<ScheduleDayDto> currentMonth(int year, int month, String company_id);

//===========================촬영 타입테이블===================================

    public List<ScheduleTimeDto> selectListScheduleTime(PageDto pageInfo);

    //일일촬영 계획표 리스트
    public List<ScheduleTimeDto> selectListDayTable2(String day_id);

    public PageDto totalSceduletimePost(PageDto dto);

    public List<ScheduleDayDto> selectFk();

    public ScheduleTimeDto selectScheduleTime(ScheduleTimeDto dto);

    public void insertTime(ScheduleTimeDto dto);

    public void updateTime(ScheduleTimeDto dto);

    public void deleteTime(ScheduleTimeDto dto);

    String searcheDayId(String time_id);
    //==============================출연자관리==========================================
    public List<ActorManagementDto> selectListActorManagement(PageDto pageInfo);

    //일일촬영 계획표 리스트
    public List<ActorManagementDto> selectListDayTable3(String day_id);

    public List<ScenarioDto> selectFkAct();

    public PageDto totalActorManagementPost(PageDto dto);

    public ActorManagementDto selectActorManagement(ActorManagementDto dto);

    //pd_id로 출연자이름찾는 메서드
    String searchActorName(String pd_id);

    public void insertActorManagement(ActorManagementDto dto);

    public void updateActorManagement(ActorManagementDto dto);

    public void deleteActorManagement(ActorManagementDto dto);

    //=============================필름플랜테이블=======================================
    public List<ScheduleDayDto> selectFkFilm();

    public List<FilmPlanDto> selectListFilmPlan(PageDto pageInfo);

    //일일 촬영계획표 1테이블 리스트생성 메소드
    public List<FilmPlanDto> selectDayTable1(String day_id);

    public PageDto totalFilmPlanPost(PageDto dto);

    public FilmPlanDto selectFilmPlan(FilmPlanDto dto);

    String selectFilmJoinActID(String pd_id, String scene_id);

    public void insertFilm(FilmPlanDto dto);

    public void updateFilm(FilmPlanDto dto);

    public void deleteFilm(FilmPlanDto dto);
}

