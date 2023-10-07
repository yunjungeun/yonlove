package com.example.yoonlove.mapper;

import com.example.yoonlove.dto.ScenarioDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ScenarioMapper {
    public List<ScenarioDto> selectListScenario();
    public ScenarioDto selectScenario(ScenarioDto dto);
    public void insertScenario(ScenarioDto dto);
    public void updateScenario(ScenarioDto dto);
    public void deleteScenario(ScenarioDto dto);

}
