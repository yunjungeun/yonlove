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


    //====================================


    private String film_id;
    private String scene_id;
    private String inside_flag;
    private String day_flag;
    private String cut;

//=========================================


    private int scene_num;
    private String scene_image;
    private String scene_place;
    private String story_board;
    private String scene_content;

//========================================

    private String act_id;

    private String pd_id;
    private String actor_info;

 //========================================



    private String pd_name;
    private String role;
    private String casting;
    private String pj_id;
    private String user_id;






}