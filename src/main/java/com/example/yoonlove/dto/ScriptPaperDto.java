package com.example.yoonlove.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class ScriptPaperDto {
    private String script_id;
    private String scene_id;
    private String video_name;
    private Date video_date;
    private Date video_time;
    private Date video_end_time;
    private String action_image;
    private String action_content;
    private String position_image;
    private String conti;

    //====================================


    private int scene_num;
    private String scene_image;
    private String scene_place;
    private String story_board;
    private String scene_content;

    //=======================================

    private String scenario_id;
    private int scenario_num;
    private String scenario_name;
    private String scenario_content;
    private String scenario_writer;
    private String scenario_date;
    private String project_id;
    private String scenario_update;

}
