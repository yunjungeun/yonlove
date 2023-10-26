package com.example.yoonlove.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter

public class ScheduleTimeDto {

    private String time_id;
    private String day_id;
    private Date film_time;
    private String film_content;



}