package com.example.yoonlove.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Controller
public class CalendarController {

    @GetMapping("/calendar")
    public String showCalendar(Model model) {
        // 현재 날짜 가져오기
        LocalDate currentDate = LocalDate.of(2023, 10, 1);

        // 한국 날짜 형식으로 출력하기 위한 포맷터
        DateTimeFormatter koreanDateFormatter = DateTimeFormatter.ofPattern("yyyy 년 MM 월 dd 일");

        // 일주일의 요일 목록
        String[] dayOfWeek = {"일", "월", "화", "수", "목", "금", "토"};

        // 현재 월의 첫 날의 요일 구하기 (0: 일요일, 1: 월요일, ..., 6: 토요일)
        int startDayOfWeek = currentDate.withDayOfMonth(1).getDayOfWeek().getValue();

        // 현재 월의 마지막 날짜 구하기
        int lastDayOfMonth = currentDate.lengthOfMonth();

        // 현재 월의 달력 생성
        List<String> calendar = new ArrayList<>();
        for (int day = 1; day <= lastDayOfMonth; day++) {
            // 날짜와 요일을 한국 날짜 형식으로 포맷하여 목록에 추가
            String formattedDate = currentDate.format(koreanDateFormatter) + " " + dayOfWeek[startDayOfWeek];
            calendar.add(formattedDate);

            // 다음 날짜로 이동
            startDayOfWeek = (startDayOfWeek + 1) % 7; // 다음 요일 계산
            currentDate = currentDate.plusDays(1); // 다음 날짜로 이동
        }

        // 뷰로 데이터를 전달하기 위해 모델에 "calendar" 속성 추가
        model.addAttribute("calendar", calendar);

        return "layout/calendar";
    }
}