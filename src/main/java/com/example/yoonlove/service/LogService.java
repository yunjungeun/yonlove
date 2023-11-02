package com.example.yoonlove.service;

import com.example.yoonlove.dto.LogDto;
import com.example.yoonlove.dto.PageDto;
import com.example.yoonlove.mapper.LogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogService {
    @Autowired
    private LogMapper logMapper;

    public List<LogDto> selectListLog(PageDto pageInfo) {
        List<LogDto> logList = logMapper.selectListLog(pageInfo);
        return logList;
    }

    public LogDto selectLog(LogDto dto) {
        LogDto logdto = logMapper.selectLog(dto);
        return logdto;  //  얘가 원인 ( dto로 해놓으면 log2만 담겨잇고 다른건 다 null로 되있음 ..)
        // return타입을 logdto로 해야됨.. !
    }

   /* public LogDto selectLog(LogDto dto) {
        LogDto dto = logMapper.selectLog(dto);   //오류 원인: LogDto dto에서 dto 변수가 겹침
        return dto;
    }*/

    public void insertLog(LogDto dto){
        logMapper.insertLog(dto);
    }

    public void updateLog(LogDto dto){
        logMapper.updateLog(dto);
    }

    public void deleteLog(LogDto dto){
        logMapper.deleteLog(dto);
    }

    public List<LogDto> currentMonthLog(int year, int month){
        return logMapper.currentMonthLog(year,month);
    }
}