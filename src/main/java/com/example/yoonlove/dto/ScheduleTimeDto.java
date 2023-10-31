package com.example.yoonlove.dto;

import lombok.Data;

@Data

public class ScheduleTimeDto {

    private String time_id;
    private String day_id;
    private String film_time;
    private String film_content;

    //db 추가 컬럼 /
    private String start_time;
    private String end_time;

    //조인테이블 dayplan_time
    private String day_title;
    private String project_id;
    private String project_name;



}