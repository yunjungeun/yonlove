package com.example.yoonlove.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter

public class ScheduleMonthDto {

    private String month_id;
    private String schedule_name;
    private String schedule_content;
    private Date schedule_date;
    private String project_id;



}