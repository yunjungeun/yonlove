package com.example.yoonlove.mapper;

import com.example.yoonlove.dto.PageDto;
import com.example.yoonlove.dto.SceneDto;
import com.example.yoonlove.dto.ScriptPaperDto;
import com.example.yoonlove.dto.TimeTableDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ScriptPaperMapper {
    //스크립트페이퍼
    public List<ScriptPaperDto> selectListScriptPaper(PageDto pageInfo);
    public PageDto totalScriptPost(PageDto dto);
    public ScriptPaperDto selectScriptPaper(ScriptPaperDto dto);
    public void insertScriptPaper(ScriptPaperDto dto);
    public void updateScriptPaper(ScriptPaperDto dto);
    public void deleteScriptPaper(ScriptPaperDto dto);
    public List<SceneDto> selectFk();

    //타임테이블
    public List<TimeTableDto> selectListTimeTable(PageDto pageInfo);
    public PageDto totalTimeTablePost(PageDto dto);
    public TimeTableDto selectTimeTable(TimeTableDto dto);
    public void insertTimeTable(TimeTableDto dto);
    public void updateTimeTable(TimeTableDto dto);
    public void deleteTimeTable(TimeTableDto dto);
}
