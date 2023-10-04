package com.example.yoonlove.mapper;

import com.example.yoonlove.dto.ScriptPaperDto;
import com.example.yoonlove.dto.TimeTableDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ScriptPaperMapper {
    //스크립트페이퍼
    public List<ScriptPaperDto> selectListScriptPaper();
    public ScriptPaperDto selectScriptPaper();
    public void insertScriptPaper();
    public void updateScriptPaper();
    public void deleteScriptPaper();

    //타임테이블
    public List<TimeTableDto> selectListTimeTable();
    public TimeTableDto selectTimeTable();
    public void insertTimeTable();
    public void updateTimeTable();
    public void deleteTimeTable();
}