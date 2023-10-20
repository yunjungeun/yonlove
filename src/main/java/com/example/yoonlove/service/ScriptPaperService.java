package com.example.yoonlove.service;

import com.example.yoonlove.dto.PageDto;
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

    //타임테이블
    public TimeTableDto selectTimeTable(TimeTableDto dto){
        return scriptPaperMapper.selectTimeTable(dto);
    }
    public List<TimeTableDto> selectListTimeTable(PageDto pageInfo) {
        return scriptPaperMapper.selectListTimeTable(pageInfo);
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
