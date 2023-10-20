package com.example.yoonlove.service;

import com.example.yoonlove.dto.PageDto;
import com.example.yoonlove.dto.ScenarioDto;
import com.example.yoonlove.mapper.ScenarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScenarioService {
    @Autowired
    private ScenarioMapper scenarioMapper;

    public ScenarioDto selectScenario(ScenarioDto dto){
        return scenarioMapper.selectScenario(dto);
    }
    public List<ScenarioDto> selectListScenario(PageDto pageInfo) {
        return scenarioMapper.selectListScenario(pageInfo);
    }
    //서비스 메소드 작성 CRUD
    public void insertScenario(ScenarioDto dto){
        scenarioMapper.insertScenario(dto);
    }
    public void updateScenario(ScenarioDto dto){
        scenarioMapper.updateScenario(dto);
    }
    public void deleteScenario(ScenarioDto dto){
        scenarioMapper.deleteScenario(dto);
    }

}
