package com.example.yoonlove.mapper;

import com.example.yoonlove.dto.ScriptPaperDto;
import com.example.yoonlove.dto.TimeTableDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ScriptPaperMapper {
    //스크립트페이퍼
    public List<ScriptPaperDto> selectListScriptPaper();
    public ScriptPaperDto selectScriptPaper(ScriptPaperDto dto);
    public void insertScriptPaper(ScriptPaperDto dto);
    public void updateScriptPaper(ScriptPaperDto dto);
    public void deleteScriptPaper(ScriptPaperDto dto);

    //타임테이블
    public List<TimeTableDto> selectListTimeTable();
    public TimeTableDto selectTimeTable(TimeTableDto dto);
    public void insertTimeTable(TimeTableDto dto);
    public void updateTimeTable(TimeTableDto dto);
    public void deleteTimeTable(TimeTableDto dto);
}
