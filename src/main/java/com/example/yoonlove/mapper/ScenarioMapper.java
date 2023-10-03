package com.example.yoonlove.mapper;

import com.example.yoonlove.Dto.ScenarioDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ScenarioMapper {
    public List<ScenarioDto> selectListScenario();
    public ScenarioDto selectScenario();
    public void insertScenario();
    public void updateScenario();
    public void deleteScenario();
}
