package com.example.yoonlove.dto;

import lombok.Data;

@Data
public class TimeTableDto {
    private String table_id;
    private String film_time;
    private int time_num;
    private String ok_ng;
    private String slate_time;
    private String script_id;

    private String formattedTime;
    private String formattedSlate;

}
