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


}