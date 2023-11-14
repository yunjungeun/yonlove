package com.example.yoonlove.service;

import com.example.yoonlove.dto.LogDto;
import com.example.yoonlove.dto.ScheduleDayDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class CalendarService {

    @Autowired
    private LogService logService;
    @Autowired
    private PlanService planService;

    //월력형 테이블 생성해주는 메소드
    public List<List<String>> generateCalendarData(int year, int month) {

        List<List<String>> calendar = new ArrayList<>();

        YearMonth yearMonth = YearMonth.of(year, month);  //연월일만 들어감 내장함수  예시) 2023-11
        int daysInMonth = yearMonth.lengthOfMonth();  //해달월의 총 일수(마지막일) 값 //31
        int startDayOfWeek = yearMonth.atDay(1).getDayOfWeek().getValue();
                                    //첫쨋날(1일)의 요일 값을 가져옴
        List<String> week = new ArrayList<>();
        //일요일로 시작하지 않으면 공란으로 채움 // 가장 첫째주 생성
        if(startDayOfWeek != 7) {
            for (int i = 1; i <= startDayOfWeek; i++) {
                week.add(""); // 앞의 빈 날짜들을 공란으로 채움
            }
        }

        for (int day = 1; day <= daysInMonth; day++) {
            week.add(String.valueOf(day));
            if (week.size() == 7) {
                calendar.add(week);
                week = new ArrayList<>();
            }
        }

        //마지막주 생성
        if (!week.isEmpty()) {
            while (week.size() < 7) {
                week.add(""); // 나머지 빈 날짜들을 null로 채움
            }
            calendar.add(week);
        }

        return calendar;
    }

    //제작일지를 json으로 만듬
    public String logJson(int year, int month, String company_id) throws JsonProcessingException {

            //제작일지 생성로직
        List<LogDto> dto = logService.currentMonthLog(year,month, company_id);
        HashMap<String, Integer> hashMap = new HashMap<>();
            for (LogDto logDto : dto) {
            String logId = logDto.getLog_id();
            String logDate = logDto.getLog_date().toString().substring(8,10); // log_date가 Date 타입인 경우 문자열로 변환
            int day = Integer.parseInt(logDate);
            // log_id를 키로 하고 log_date의 일자를 값으로 하는 해쉬맵에 추가
            hashMap.put(logId, day);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        // 해시맵을 JSON 문자열로 변환
        String jsonString = objectMapper.writeValueAsString(hashMap);
        System.out.println(jsonString);
        return jsonString;
    }

    //일일촬영계획을 json으로 만듬
    public String dayJson(int year, int month, String company_id) throws JsonProcessingException {

        //제작일지 생성로직
        List<ScheduleDayDto> dto = planService.currentMonth(year,month, company_id);
        HashMap<String, Integer> hashMap = new HashMap<>();
        for (ScheduleDayDto dayDto : dto) {
            String dayId = dayDto.getDay_id();
            String dayDate = dayDto.getFilm_date().toString().substring(8,10); // log_date가 Date 타입인 경우 문자열로 변환

            int day = Integer.parseInt(dayDate);
            // log_id를 키로 하고 log_date의 일자를 값으로 하는 해쉬맵에 추가
            hashMap.put(dayId, day);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        // 해시맵을 JSON 문자열로 변환
        String jsonString = objectMapper.writeValueAsString(hashMap);

        return jsonString;
    }
}