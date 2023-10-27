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

//==============================================================

    public List<ScheduleTimeDto> selectListScheduleTime(PageDto pageInfo);
    public List<ScheduleTimeDto> selectListDayTable2(String day_id);
    public PageDto totalSceduletimePost(PageDto dto);
    public List<ScheduleDayDto> selectFk();
    public ScheduleTimeDto selectScheduleTime(ScheduleTimeDto dto);
    public void insertTime(ScheduleTimeDto dto);
    public void updateTime(ScheduleTimeDto dto);
    public void deleteTime(ScheduleTimeDto dto);
//========================================================================
    public List<ActorManagementDto> selectListActorManagement(PageDto pageInfo);
    public PageDto totalActorManagementPost(PageDto dto);
    public ActorManagementDto selectActorManagement(ActorManagementDto dto);
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
    public void insertFilm(FilmPlanDto dto);
    public void updateFilm(FilmPlanDto dto);
    public void deleteFilm(FilmPlanDto dto);

//==============================월간계획======================================

    public List<ScheduleMonthDto> selectListPlan(PageDto pageInfo);
    public PageDto totalScheduleMonthPost(PageDto dto);
    public ScheduleMonthDto selectPlan(ScheduleMonthDto dto);
    public void insertPlan(ScheduleMonthDto dto);
    public void updatePlan(ScheduleMonthDto dto);
    public void deletePlan(ScheduleMonthDto dto);
}