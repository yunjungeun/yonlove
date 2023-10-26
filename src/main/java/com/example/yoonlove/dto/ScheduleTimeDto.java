package com.example.yoonlove.dto;

import lombok.Data;

import java.sql.Date;

@Data

public class ScheduleTimeDto {

    private String time_id;
    private String day_id;
    private Date film_time;
    private String film_content;

    //조인테이블 dayplan_time
    private String day_title;
    private String project_id;
    private String project_name;



}