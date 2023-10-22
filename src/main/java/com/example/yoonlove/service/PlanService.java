package com.example.yoonlove.service;


import com.example.yoonlove.dto.*;
import com.example.yoonlove.mapper.PlanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PlanService {

    @Autowired
    private PlanMapper planMapper;


    public List<ScheduleDayDto> selectListSchedule(PageDto pageInfo) {
        List<ScheduleDayDto> scheduleList = planMapper.selectListSchedule(pageInfo);
        return scheduleList;
    }

    public ScheduleDayDto  selectSchedule(ScheduleDayDto dto) {
        ScheduleDayDto  scheduleDetail = planMapper.selectSchedule(dto);
        return scheduleDetail;
    }

    public void insertSchedule(ScheduleDayDto dto) {


        planMapper.insertSchedule(dto);
    }

    public void updateSchedule(ScheduleDayDto dto) {
        planMapper.updateSchedule(dto);

    }

    public void deleteSchedule(ScheduleDayDto dto) {
        planMapper.deleteSchedule(dto);

    }





//================================================================================

    public List<ScheduleTimeDto> selectListScheduleTime(PageDto pageInfo) {
        List<ScheduleTimeDto> scheduleTimeList = planMapper.selectListScheduleTime(pageInfo);
        return scheduleTimeList;
    }

    public ScheduleTimeDto  selectScheduleTime(ScheduleTimeDto dto) {
        ScheduleTimeDto  scheduleTimeDetail = planMapper.selectScheduleTime(dto);
        return scheduleTimeDetail;
    }



    public void insertTime(ScheduleTimeDto dto) {


        planMapper.insertTime(dto);
    }

    public void updateTime(ScheduleTimeDto dto) {
        planMapper.updateTime(dto);

    }

    public void deleteTime(ScheduleTimeDto dto) {
        planMapper.deleteTime(dto);

    }


//===================================================================================


    public List<ActorManagementDto> selectListActorManagement(PageDto pageInfo) {
        List<ActorManagementDto> ActorManagementList = planMapper.selectListActorManagement(pageInfo);
        return ActorManagementList;
    }

    public ActorManagementDto  selectActorManagement(ActorManagementDto dto) {
        ActorManagementDto  ActorManagementDetail = planMapper.selectActorManagement(dto);
        return ActorManagementDetail;
    }

    public void insertActorManagement(ActorManagementDto dto) {


        planMapper.insertActorManagement(dto);
    }

    public void updateActorManagement(ActorManagementDto dto) {
        planMapper.updateActorManagement(dto);

    }

    public void deleteActorManagement(ActorManagementDto dto) {
        planMapper.deleteActorManagement(dto);

    }

    //================================================

    public List<FilmPlanDto> selectListFilmPlan(PageDto pageInfo) {
        List<FilmPlanDto> FilmPlanList = planMapper.selectListFilmPlan(pageInfo);
        return FilmPlanList;
    }

    public FilmPlanDto selectFilmPlan(FilmPlanDto dto) {
        FilmPlanDto  ActorManagementDetail = planMapper.selectFilmPlan(dto);
        return ActorManagementDetail;
    }

    public void insertFilm(FilmPlanDto dto) {


        planMapper.insertFilm(dto);
    }


    public void updateFilm(FilmPlanDto dto) {
        planMapper.updateFilm(dto);
    }

    public void deleteFilm(FilmPlanDto dto) {
        planMapper.deleteFilm(dto);

    }

    //============================================


    public List<ScheduleMonthDto> selectListPlan(PageDto pageInfo) {
        List<ScheduleMonthDto> planList = planMapper.selectListPlan(pageInfo);
        return planList;
    }

    public ScheduleMonthDto selectPlan(ScheduleMonthDto dto) {
        ScheduleMonthDto planDetail = planMapper.selectPlan(dto);
        return planDetail;
    }

    public void insertPlan(ScheduleMonthDto dto) {
        planMapper.insertPlan(dto);
    }

    public void updatePlan(ScheduleMonthDto dto) {
        planMapper.updatePlan(dto);
    }

    public void deletePlan(ScheduleMonthDto dto) {
        planMapper.deletePlan(dto);
    }




}













































