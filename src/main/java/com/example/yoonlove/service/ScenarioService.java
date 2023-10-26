package com.example.yoonlove.service;

import com.example.yoonlove.dto.PageDto;
import com.example.yoonlove.dto.ProjectDto;
import com.example.yoonlove.dto.ScenarioDto;
import com.example.yoonlove.mapper.ScenarioMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    //옵션 해쉬맵을 생성하는 메소드
    /*맵을 쓰는 이유 : html에서'프로퍼티': 사용자에게 보여줄 '실제값' 구분하기 위해서임.
        html과 서버에서는 프로퍼티를 가지고 데이터를 처리함. 주로 pk-id값
        그러나 사용자는 pk값을 구분 할수 없어 'pk'가 가진 데이터를 보여줘야함.
        따라서 'pk(fk)':'보여줄 값' 형식이기 때문에 해쉬맵을 사용함*/
    public String fkJson(List<ProjectDto> dto) throws JsonProcessingException {
        Map<String, String> fkList = new LinkedHashMap<>();//해쉬맵은 삽입순서를 유지하지 않기 때문에, LinkedHashMap<>으로 사용자 편의를 위한 정렬삽입을 했음
        for(int i=0; i< dto.size(); i++){
                        //           fk 값       /   실제 표시딜 이름값
            fkList.put(dto.get(i).getProject_id(),dto.get(i).getProject_name());
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonList = objectMapper.writeValueAsString(fkList);
        return jsonList;
    }
    //fk로 db를list를 출력하는 메소드
    public List<ProjectDto> selectFk(){
        return scenarioMapper.selectFk();
    }
}
