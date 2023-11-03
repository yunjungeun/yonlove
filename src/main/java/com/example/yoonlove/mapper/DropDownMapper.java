package com.example.yoonlove.mapper;

import com.example.yoonlove.dto.ProjectDto;
import com.example.yoonlove.dto.ScenarioDto;
import com.example.yoonlove.dto.SceneDto;
import com.example.yoonlove.dto.ScheduleDayDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DropDownMapper {

    //dropDownOption 메소드에 있는 sql실행메소드 start
    List<ScenarioDto> selectFkScenario(String ajaxData, String company_id);
    List<SceneDto> selectFkScene(String ajaxData, String company_id);
    List<ProjectDto> selectFkProject(String ajaxData, String company_id);
    List<ScheduleDayDto> selectFkDay(String ajaxData, String company_id);
    //dropDownOption 메소드 end------------>


    //프로젝트 옵션선택 값에 종속된 시나리오 검색
    List<ScenarioDto> selectScenario(String pkId);
    //프로젝트 옵션선택 값에 종속된 일일촬영계획표 검색
}



