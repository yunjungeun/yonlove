package com.example.yoonlove.service;

import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Service
public class CalendarService {
    public static List<List<String>> generateCalendarData(int year, int month) {
        List<List<String>> calendar = new ArrayList<>();

        YearMonth yearMonth = YearMonth.of(year, month);  //연월일만 들어감 내장함수  예시) 2023-11
        int daysInMonth = yearMonth.lengthOfMonth();  //해달월의 총 일수(마지막일) 값 //31
        int startDayOfWeek = yearMonth.atDay(1).getDayOfWeek().getValue();
                                    //첫쨋날(1일)의 요일 값을 가져옴
        List<String> week = new ArrayList<>();
        //일요일로 시작하지 않으면 공란으로 채움
        if(startDayOfWeek != 7) {
            for (int i = 1; i < startDayOfWeek; i++) {
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

        if (!week.isEmpty()) {
            while (week.size() < 7) {
                week.add(""); // 나머지 빈 날짜들을 null로 채움
            }
            calendar.add(week);
        }

        return calendar;
    }
}
