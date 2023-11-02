
package com.example.yoonlove.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


@Data
public class CalendarEventDto {
    private int year;
    private int month;
    private int day;
    private String event;

    public CalendarEventDto(int year, int month, int day, String event){
        this.year = year;
        this.month = month;
        this.day = day;
        this.event = event;
    }

    public String getKoreanDate() {
        Calendar lunarCalendar = Calendar.getInstance();
        lunarCalendar.set(year, month - 1, day);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setCalendar(lunarCalendar);

        Date lunarDate = lunarCalendar.getTime();
        return dateFormat.format(lunarDate); //한국날짜형식으로 변환
    }

    @Override
    public String toString() {
        return "CalendarEventDto{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                ", event='" + event + '\'' +
                '}';
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public String getEvent() {
        return event;
    }
}




