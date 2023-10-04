package com.example.yoonlove.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
public class TimeTableDto {
    private String table_id;
    private Date film_time;
    private int time_nume;
    private String ok_ng;
    private Date slate_time;
    private String script_id;
}
