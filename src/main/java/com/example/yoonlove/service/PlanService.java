package com.example.yoonlove.service;


import com.example.yoonlove.dto.*;
import com.example.yoonlove.mapper.PlanMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


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

    public List<ProjectDto> selectFkDay(){
        return planMapper.selectFkDay();
    }

    //옵션헤쉬맵
    public String fkJsonDay(List<ProjectDto> dto) throws JsonProcessingException {
        Map<String, String> fkList = new LinkedHashMap<>();//해쉬맵은 삽입순서를 유지하지 않기 때문에, LinkedHashMap<>으로 사용자 편의를 위한 정렬삽입을 했음
        for(int i=0; i< dto.size(); i++){
            fkList.put(dto.get(i).getProject_id(),dto.get(i).getProject_name());
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonList = objectMapper.writeValueAsString(fkList);
        return jsonList;
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





//==============================스케쥴타임==================================================

    public List<ScheduleTimeDto> selectListScheduleTime(PageDto pageInfo) {
        List<ScheduleTimeDto> scheduleTimeList = planMapper.selectListScheduleTime(pageInfo);
        return scheduleTimeList;
    }
    //일일촬영계획표 리스트
    public List<ScheduleTimeDto> selectListDayTable2(String day_id){
        List<ScheduleTimeDto> scheduleTimeDto = planMapper.selectListDayTable2(day_id);
        return scheduleTimeDto;
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

    //옵션 fk검색
    public List<ScheduleDayDto> selectFk(){
        return planMapper.selectFk();
    }
    //옵션헤쉬맵
    public String fkJson(List<ScheduleDayDto> dto) throws JsonProcessingException {
        Map<String, String> fkList = new LinkedHashMap<>();//해쉬맵은 삽입순서를 유지하지 않기 때문에, LinkedHashMap<>으로 사용자 편의를 위한 정렬삽입을 했음
        for(int i=0; i< dto.size(); i++){
            fkList.put(dto.get(i).getDay_id(),dto.get(i).getDay_title());
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonList = objectMapper.writeValueAsString(fkList);
        return jsonList;
    }
//==================================출연자관리=================================================



    public List<ActorManagementDto> selectListActorManagement(PageDto pageInfo) {
        List<ActorManagementDto> ActorManagementList = planMapper.selectListActorManagement(pageInfo);
        return ActorManagementList;
    }
    //일일촬영계획표 리스트
    public List<ActorManagementDto> selectListDayTable3(String day_id){
        List<ActorManagementDto> actorManagementDto = planMapper.selectListDayTable3(day_id);
        return actorManagementDto;
    }

    public List<ScenarioDto> selectFkAct(){
        return planMapper.selectFkAct();
    }
    //옵션헤쉬맵
    public String fkJsonAct(List<ScenarioDto> dto) throws JsonProcessingException {
        Map<String, String> fkList = new LinkedHashMap<>();//해쉬맵은 삽입순서를 유지하지 않기 때문에, LinkedHashMap<>으로 사용자 편의를 위한 정렬삽입을 했음
        for(int i=0; i< dto.size(); i++){
            fkList.put(dto.get(i).getScenario_id(),dto.get(i).getScenario_name());
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonList = objectMapper.writeValueAsString(fkList);
        return jsonList;
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
    public List<ScheduleDayDto> selectFkFilm(){
        return planMapper.selectFkFilm();
    }

    //옵션헤쉬맵
    public String fkJsonFilm(List<ScheduleDayDto> dto) throws JsonProcessingException {
        Map<String, String> fkList = new LinkedHashMap<>();//해쉬맵은 삽입순서를 유지하지 않기 때문에, LinkedHashMap<>으로 사용자 편의를 위한 정렬삽입을 했음
        for(int i=0; i< dto.size(); i++){
            fkList.put(dto.get(i).getDay_id(),dto.get(i).getDay_title());
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonList = objectMapper.writeValueAsString(fkList);
        return jsonList;
    }

    public List<FilmPlanDto> selectListFilmPlan(PageDto pageInfo) {
        List<FilmPlanDto> FilmPlanList = planMapper.selectListFilmPlan(pageInfo);
        return FilmPlanList;
    }

    //일일 촬영계획포 1테이블 리스트 생성 메소드
    public List<FilmPlanDto> selectListDayTable1(String day_id){
        List<FilmPlanDto> dayTable1 = planMapper.selectDayTable1(day_id);
        return dayTable1;
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