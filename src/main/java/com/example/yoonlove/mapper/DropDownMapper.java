package com.example.yoonlove.mapper;

import com.example.yoonlove.dto.ProjectDto;
import com.example.yoonlove.dto.ScenarioDto;
import com.example.yoonlove.dto.SceneDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DropDownMapper {

    List<ScenarioDto> selectFkScenario(String ajaxData);
    List<SceneDto> selectFkScene(String ajaxData);
    List<ProjectDto> selectFkProject(String ajaxData);

    //프로젝트 옵션선택 값에 종속된 시나리오 검색
    List<ScenarioDto> selectScenario(String pkId);
}
