package com.example.yoonlove.dto;

import lombok.Data;

@Data
public class ActorManagementDto {

    private String actor_id;
    private String actor_name;
    private String arrival_time;
    private String place;
    private String set_call;
    private String day_id;

    //조인테이블 : actor_management_actor
    private String act_id;
    private String scene_id;
    private String pd_id;
    private String actor_info;
    private String scenario_id;
    private String scenario_name;
    private int scene_num;
}