package com.example.yoonlove.dto;

import lombok.Data;


@Data
public class SceneDto {
    private String scene_id;
    private int scene_num;
/*    private String scene_image;*/
    private String scene_place;
    private String story_board;
    private String scene_content;



   //==============조인테이블 : storyboardlist

    private String scenario_id;
    private int scenario_num;
    private String scenario_name;
    private String scenario_content;
    private String scenario_writer;
    private String scenario_date;
    private String project_id;
    private String scenario_update;
    private String company_id;
}
