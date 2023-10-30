package com.example.yoonlove.mapper;

import com.example.yoonlove.dto.ProjectDto;
import com.example.yoonlove.dto.ScenarioDto;
import com.example.yoonlove.dto.SceneDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DropDownMapper {

    List<ScenarioDto> selectFkScenario(String table, String pkId, String pkName);
    List<SceneDto> selectFkScene(String table, String pkId, String pkName);
    List<ProjectDto> selectFkProject(String table, String pkId, String pkName);
}
