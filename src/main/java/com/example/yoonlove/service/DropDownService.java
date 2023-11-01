package com.example.yoonlove.service;

import com.example.yoonlove.dto.ProjectDto;
import com.example.yoonlove.dto.ScenarioDto;
import com.example.yoonlove.dto.SceneDto;
import com.example.yoonlove.dto.ScheduleDayDto;
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
                                    //매개변수 ajaxData는 ajax에서 넘어오는 key 속성임
                                    // 드롭다운컨트롤러에서만 쓰이고 다른 컨트롤러에서는 null값을 넣으세요
    public String dropDownOption(String table, String ajaxData)throws JsonProcessingException{
        //문자열, 숫자 해시맵 + 문자열,문자열 해쉬맵 객체생성
        Map<String, Integer> fkIntList = new LinkedHashMap<>();
        Map<String, String> fkStringList = new LinkedHashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonList = null;

        //리스트 타입이 여러개이기 때문에 스위치문으로 작성 / 인터페이스로 바꾸면 간소화 가능 //추후 인터페이스로 변형
        switch (table) {
            case "scenario" :
                List<ScenarioDto> scenarioDtos = dropDownMapper.selectFkScenario(ajaxData);
                for(int i=0; i< scenarioDtos.size(); i++){
                    fkStringList.put(scenarioDtos.get(i).getScenario_id(),scenarioDtos.get(i).getScenario_name());
                }
                break;
            case "scene" :
                List<SceneDto> sceneDtos = dropDownMapper.selectFkScene(ajaxData);
                for(int i=0; i< sceneDtos.size(); i++){
                    fkIntList.put(sceneDtos.get(i).getScene_id(),sceneDtos.get(i).getScene_num());
                }
                break;
            case "project" :
                List<ProjectDto> projectDtos = dropDownMapper.selectFkProject(ajaxData);
                for(int i=0; i< projectDtos.size(); i++){
                    fkStringList.put(projectDtos.get(i).getProject_id(),projectDtos.get(i).getProject_name());
                }
                break;
            case "schedule_day" :
                List<ScheduleDayDto> scheduleDayDtos = dropDownMapper.selectFkDay(ajaxData);
                for(int i=0; i< scheduleDayDtos.size(); i++){
                    fkStringList.put(scheduleDayDtos.get(i).getDay_id(),scheduleDayDtos.get(i).getDay_title());
                }
                break;
        }

        if(fkIntList.isEmpty()){
            jsonList = objectMapper.writeValueAsString(fkStringList);
        }else {
            jsonList = objectMapper.writeValueAsString(fkIntList);
        }
        return jsonList;
    }

    //프로젝트 옵션 선택에따라 시나리오 옵션을 재생성
    public String scenarioOption(String pkId) throws JsonProcessingException{
        String jsonList = dropDownOption("scenario", pkId);
        return jsonList;
    }

    //시나리오 옵션 선택에따라 씬 옵션을 재생성
    public String sceneOption(String pkId) throws JsonProcessingException{
        String jsonList = dropDownOption("scene", pkId);
        return jsonList;
    }
    //시나리오 옵션 선택에따라 씬 옵션을 재생성
    public String dayOption(String pkId) throws JsonProcessingException{
        String jsonList = dropDownOption("schedule_day", pkId);
        return jsonList;
    }
}
