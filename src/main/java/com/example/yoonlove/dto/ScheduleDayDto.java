package com.example.yoonlove.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class ScheduleDayDto {
    private String day_id;
    private Date film_date;
    private String weather;
    private String day_title;
    private String light;
    private String costume;
    private String prop;
    private String film_order;
    private String direct;
    private String project_id;
    private String day_num;

    //조인테이블 -  scheduledaylist
    private String project_name;
    private String company_id;
    private String project_state;
}