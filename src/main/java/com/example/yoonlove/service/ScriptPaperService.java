package com.example.yoonlove.service;

import com.example.yoonlove.dto.ScriptPaperDto;
import com.example.yoonlove.dto.TimeTableDto;
import com.example.yoonlove.mapper.ScriptPaperMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScriptPaperService {
    @Autowired
    private ScriptPaperMapper scriptPaperMapper;

    public ScriptPaperDto selectScriptPaper(){
        return scriptPaperMapper.selectScriptPaper();
    }
    public List<ScriptPaperDto> selectListScriptPaper() {
        return scriptPaperMapper.selectListScriptPaper();
    }
    public void insertScriptPaper(){
        scriptPaperMapper.insertScriptPaper();
    }
    public void updateScriptPaper(){
        scriptPaperMapper.updateScriptPaper();
    }
    public void deleteScriptPaper(){
        scriptPaperMapper.deleteScriptPaper();
    }

    //타임테이블
    public TimeTableDto selectTimeTable(){
        return scriptPaperMapper.selectTimeTable();
    }
    public List<TimeTableDto> selectListTimeTable() {
        return scriptPaperMapper.selectListTimeTable();
    }
    public void insertTimeTable(){
        scriptPaperMapper.insertTimeTable();
    }
    public void updateTimeTable(){
        scriptPaperMapper.updateTimeTable();
    }
    public void deleteTimeTable(){
        scriptPaperMapper.deleteTimeTable();
    }
}
