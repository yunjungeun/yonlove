package com.example.yoonlove.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SceneDto {
    private String scene_id;
    private int scene_num;
    private String scene_image;
    private String scene_place;
    private String story_board;
    private String scene_content;
    private String scenario_id;


    @Override
    public String toString() {
        return "SceneDto{" +
                "scene_id='" + scene_id + '\'' +
                ", scene_num=" + scene_num +
                ", scene_image='" + scene_image + '\'' +
                ", scene_place='" + scene_place + '\'' +
                ", story_board='" + story_board + '\'' +
                ", scene_content='" + scene_content + '\'' +
                ", scenario_id='" + scenario_id + '\'' +
                '}';
    }
}
