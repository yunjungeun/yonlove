package com.example.yoonlove.mapper;

import com.example.yoonlove.dto.PageDto;
import com.example.yoonlove.dto.ProjectDto;
import com.example.yoonlove.dto.ScenarioDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ScenarioMapper {
    public List<ScenarioDto> selectListScenario(PageDto pageInfo);
    public PageDto totalScenarioPost(PageDto dto);
    public List<ProjectDto> selectFk();
    public ScenarioDto selectScenario(ScenarioDto dto);
    public void insertScenario(ScenarioDto dto);
    public void updateScenario(ScenarioDto dto);
    public void deleteScenario(ScenarioDto dto);

}
