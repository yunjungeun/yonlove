package com.example.yoonlove.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActorDto {
    private String act_id;
    private String scene_id;
    private String pd_id;
    private String actor_info;

    //조인테이블
    private String scenario_name;
    private String company_id;
    private String project_name;
    private String pd_name;
    private String casting;
    private int scene_num;
    private String day_id;
}
