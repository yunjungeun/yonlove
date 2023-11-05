package com.example.yoonlove.service;

import com.example.yoonlove.dto.*;
import com.example.yoonlove.mapper.FileMapper;
import com.example.yoonlove.mapper.SceneMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class SceneService {
    @Autowired
    private SceneMapper sceneMapper;

    @Autowired
    private FileMapper fileMapper;

    public SceneDto selectScene(SceneDto dto) {
        return sceneMapper.selectScene(dto);
    }
    public List<SceneDto> selectListScene(PageDto pageInfo) {
        return sceneMapper.selectListScene(pageInfo);
    }

    //fk로 db를list를 출력하는 메소드
    public List<ScenarioDto> selectFk(){
        return sceneMapper.selectFk();
    }

    //업로드관련 메소드인데 정확히 어떤 역할인지 작성바랍니다.
    public int lastPost(SceneDto dto){return sceneMapper.lastPost(dto);}

    public void insertScene(SceneDto dto){
        sceneMapper.insertScene(dto);
    }
    public void updateScene(SceneDto dto){
        sceneMapper.updateScene(dto);
    }
    public void deleteScene(SceneDto dto){
        sceneMapper.deleteScene(dto);
    }


    //옵션 해쉬맵을 생성하는 메소드
    /*맵을 쓰는 이유 : html에서'프로퍼티': 사용자에게 보여줄 '실제값' 구분하기 위해서임.
        html과 서버에서는 프로퍼티를 가지고 데이터를 처리함. 주로 pk-id값
        그러나 사용자는 pk값을 구분 할수 없어 'pk'가 가진 데이터를 보여줘야함.
        따라서 'pk(fk)':'보여줄 값' 형식이기 때문에 해쉬맵을 사용함*/
    public String fkJson(List<ScenarioDto> dto) throws JsonProcessingException {
        Map<String, String> fkList = new LinkedHashMap<>();//해쉬맵은 삽입순서를 유지하지 않기 때문에, LinkedHashMap<>으로 사용자 편의를 위한 정렬삽입을 했음
            for(int i=0; i< dto.size(); i++){
                fkList.put(dto.get(i).getScenario_id(),dto.get(i).getScenario_name());
              }
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonList = objectMapper.writeValueAsString(fkList);
        return jsonList;
    }

    //출연자정보
    public ActorDto selectActor(ActorDto dto){
        return sceneMapper.selectActor(dto);
    }
    public List<ActorDto> selectListActor(PageDto pageInfo) {
        return sceneMapper.selectListActor(pageInfo);
    }
    public void insertActor(ActorDto dto){
        sceneMapper.insertActor(dto);
    }
    public void updateActor(ActorDto dto){
        sceneMapper.updateActor(dto);
    }

    //actdto를 produceDto로 전환해주는 메소드
    public ProduceDto transToProduceDto(ActorDto dto){
        ProduceDto produceDto = new ProduceDto();
        produceDto.setPd_id(dto.getPd_id());
        produceDto.setCasting(dto.getCasting());
        produceDto.setPd_name(dto.getPd_name());
        return produceDto;
    }

    public void deleteActor(ActorDto dto){
        sceneMapper.deleteActor(dto);
    }
}
