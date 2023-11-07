package com.example.yoonlove.mapper;

import com.example.yoonlove.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DropDownMapper {

    //dropDownOption 메소드에 있는 sql실행메소드 start
    List<ScenarioDto> selectFkScenario(String ajaxData, String company_id);
    List<SceneDto> selectFkScene(String ajaxData, String company_id);
    List<ProjectDto> selectFkProject(String ajaxData, String company_id);
    List<ScheduleDayDto> selectFkDay(String ajaxData, String company_id);
    List<ProduceDto> selectFkProduce(String ajaxData);
    List<FilmPlanDto> selectFkTable1(String ajaxData);
    //dropDownOption 메소드 end------------>


    //프로젝트 옵션선택 값에 종속된 시나리오 검색
    List<ScenarioDto> selectScenario(String pkId);
    //프로젝트 옵션선택 값에 종속된 일일촬영계획표 검색
}



