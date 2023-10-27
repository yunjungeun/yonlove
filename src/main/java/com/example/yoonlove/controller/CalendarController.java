/*
package com.example.yoonlove.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CalendarController {

    @GetMapping("/calendar")
    public ModelAndView showCalendar() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/layout/newcal");
        return mv;
    }
}

*/


package com.example.yoonlove.controller;

import com.example.yoonlove.dto.CalendarEventDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller
public class CalendarController {
    @GetMapping("/calendar")
    public ModelAndView showCalendar(){
        ModelAndView mv= new ModelAndView();
        // 달력 데이터 생성
        List<CalendarEventDto> events = createCalendarData();

        mv.setViewName("/layout/calendar");
        mv.addObject("event", events);
        return mv;
    }

    private List<CalendarEventDto> createCalendarData(){
        // 달력 데이터 생성 로직
        List<CalendarEventDto> events = new ArrayList<>();
        for (int month = 1; month <= 12; month++) {
            for (int day = 1; day <= daysInMonth(month); day++) {
                events.add(new CalendarEventDto(Calendar.getInstance().get(Calendar.YEAR), month, day, "Sample Event"));
            }
        }

        return events;
    }

    private int daysInMonth(int month) {
        int[] daysInMonth = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        return daysInMonth[month];
    }
}