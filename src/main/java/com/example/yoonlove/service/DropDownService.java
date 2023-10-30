package com.example.yoonlove.service;

import com.example.yoonlove.dto.ProjectDto;
import com.example.yoonlove.dto.ScenarioDto;
import com.example.yoonlove.dto.SceneDto;
import com.example.yoonlove.mapper.DropDownMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class DropDownService {

    @Autowired
    private DropDownMapper dropDownMapper;

    public String test(String table, String pkId, String pkName)throws JsonProcessingException{
        //문자열, 숫자 해시맵 + 문자열,문자열 해쉬맵 객체생성
        Map<String, Integer> fkIntList = new LinkedHashMap<>();
        Map<String, String> fkStringList = new LinkedHashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonList = null;

        //리스트 타입이 여러개임
        switch (table) {
            case "scenario" :
                List<ScenarioDto> scenarioDtos = dropDownMapper.selectFkScenario(table, pkId, pkName);
                for(int i=0; i< scenarioDtos.size(); i++){
                    fkStringList.put(scenarioDtos.get(i).getScenario_id(),scenarioDtos.get(i).getScenario_name());
                }
                break;
            case "scene" :
                List<SceneDto> sceneDtos = dropDownMapper.selectFkScene(table, pkId, pkName);
                for(int i=0; i< sceneDtos.size(); i++){
                    fkIntList.put(sceneDtos.get(i).getScene_id(),sceneDtos.get(i).getScene_num());
                }
                break;
            case "project" :
                List<ProjectDto> projectDtos = dropDownMapper.selectFkProject(table, pkId, pkName);
                for(int i=0; i< projectDtos.size(); i++){
                    fkStringList.put(projectDtos.get(i).getProject_id(),projectDtos.get(i).getProject_name());
                }
                break;
        }

        if(fkIntList != null){
            jsonList = objectMapper.writeValueAsString(fkIntList);
        }else {
            jsonList = objectMapper.writeValueAsString(fkStringList);
        }
        return jsonList;
    }
}
