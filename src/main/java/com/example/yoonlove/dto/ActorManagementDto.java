package com.example.yoonlove.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter

public class ActorManagementDto {

    private String actor_id;
    private String actor_name;
    private Date arrival_time;
    private String place;
    private String set_call;
    private String day_id;


    //====================================출연진관리,일일촬영계획표,프로젝트 3중조인



    private Date film_date;
    private String weather;
    private String day_title;
    private String light;
    private String costume;
    private String prop;
    private String film_order;
    private String direct;
    private String project_id;

//==============================================


    private String scene_id;
    private int scene_num;
    private String scene_image;
    private String scene_place;
    private String story_board;
    private String scene_content;


 //=================================================



    private String film_id;
    private String inside_flag;
    private String day_flag;
    private String cut;


    //===============================================

    private String act_id;
    private String pd_id;
    private String actor_info;

    //===========================================


    private String pd_name;
    private String role;
    private String casting;
    private String user_id;

}
