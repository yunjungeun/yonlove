package com.example.yoonlove.service;

import com.example.yoonlove.dto.PageDto;
import com.example.yoonlove.dto.SceneDto;
import com.example.yoonlove.dto.ScriptPaperDto;
import com.example.yoonlove.dto.TimeTableDto;
import com.example.yoonlove.mapper.ScriptPaperMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ScriptPaperService {
    @Autowired
    private ScriptPaperMapper scriptPaperMapper;

    public ScriptPaperDto selectScriptPaper(ScriptPaperDto dto){
        return scriptPaperMapper.selectScriptPaper(dto);
    }
    public List<ScriptPaperDto> selectListScriptPaper(PageDto pageInfo) {
        return scriptPaperMapper.selectListScriptPaper(pageInfo);
    }
    public void insertScriptPaper(ScriptPaperDto dto){
        scriptPaperMapper.insertScriptPaper(dto);
    }
    public void updateScriptPaper(ScriptPaperDto dto){
        scriptPaperMapper.updateScriptPaper(dto);
    }
    public void deleteScriptPaper(ScriptPaperDto dto){
        scriptPaperMapper.deleteScriptPaper(dto);
    }

    //insert scirpt 뷰페이지 옵션값을 생성해주는 메소드
    public String fkJson(List<SceneDto> dto) throws JsonProcessingException {
        Map<String, Integer> fkList = new LinkedHashMap<>();//해쉬맵은 삽입순서를 유지하지 않기 때문에, LinkedHashMap<>으로 사용자 편의를 위한 정렬삽입을 했음
        for(int i=0; i< dto.size(); i++){
            fkList.put(dto.get(i).getScene_id(),dto.get(i).getScene_num());
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonList = objectMapper.writeValueAsString(fkList);
        return jsonList;
    }

    public List<SceneDto> selectFk(){
        return scriptPaperMapper.selectFk();
    }

    public List<TimeTableDto> selectTimeFk(PageDto pageInfo){
        return scriptPaperMapper.selectTimeFk(pageInfo);
    }

    //타임테이블
    public TimeTableDto selectTimeTable(TimeTableDto dto){
            return scriptPaperMapper.selectTimeTable(dto);
    }

    public HashMap<String, Boolean> okFlagCheck(TimeTableDto dto){
        HashMap<String, Boolean> okList = new LinkedHashMap<>();
        String[] sign = {"OK","NG"};
        for(int i = 0; i < sign.length; i++){
            if(sign[i].equals(dto.getOk_ng())){
                okList.put(sign[i],true);
            }else {
                okList.put(sign[i], false);
            }
        }
        return okList;
    }

    public List<TimeTableDto> selectListTimeTable(PageDto pageInfo) {
    return  scriptPaperMapper.selectListTimeTable(pageInfo);
    }
    public void insertTimeTable(TimeTableDto dto){
        scriptPaperMapper.insertTimeTable(dto);
    }
    public void updateTimeTable(TimeTableDto dto){
        scriptPaperMapper.updateTimeTable(dto);
    }
    public void deleteTimeTable(TimeTableDto dto){
        scriptPaperMapper.deleteTimeTable(dto);
    }
}
