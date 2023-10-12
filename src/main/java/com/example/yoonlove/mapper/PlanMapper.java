package com.example.yoonlove.mapper;

import com.example.yoonlove.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlanMapper {




    public List<ScheduleDayDto> selectListSchedule();
    public ScheduleDayDto selectSchedule(ScheduleDayDto dto);
    public void insertSchedule(ScheduleDayDto dto);
    public void updateSchedule(ScheduleDayDto dto);
    public void deleteSchedule(ScheduleDayDto dto);



    public List<ScheduleTimeDto> selectListScheduleTime();
    public ScheduleTimeDto selectScheduleTime(ScheduleTimeDto dto);
    public void insertTime(ScheduleTimeDto dto);
    public void updateTime(ScheduleTimeDto dto);
    public void deleteTime(ScheduleTimeDto dto);

    public List<ActorManagementDto> selectListActorManagement();
    public ActorManagementDto selectActorManagement(ActorManagementDto dto);
    public void insertActorManagement(ActorManagementDto dto);
    public void updateActorManagement(ActorManagementDto dto);
    public void deleteActorManagement(ActorManagementDto dto);

    public List<FilmPlanDto> selectListFilmPlan();
    public FilmPlanDto selectFilmPlan(FilmPlanDto dto);
    public void insertFilm(FilmPlanDto dto);
    public void updateFilm(FilmPlanDto dto);
    public void deleteFilm(FilmPlanDto dto);



    public List<ScheduleMonthDto> selectListPlan();
    public ScheduleMonthDto selectPlan(ScheduleMonthDto dto);
    public void insertPlan(ScheduleMonthDto dto);
    public void updatePlan(ScheduleMonthDto dto);
    public void deletePlan(ScheduleMonthDto dto);



}