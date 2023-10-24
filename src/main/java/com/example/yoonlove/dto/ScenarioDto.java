package com.example.yoonlove.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScenarioDto {
    private String scenario_id;
    private int scenario_num;
    private String scenario_name;
    private String scenario_content;
    private String scenario_writer;
    private String scenario_date;
    private String project_id;
    private String scenario_update;

    @Override
    public String toString() {
        return "ScenarioDto{" +
                "scenario_id='" + scenario_id + '\'' +
                ", scenario_num=" + scenario_num +
                ", scenario_name='" + scenario_name + '\'' +
                ", scenario_content='" + scenario_content + '\'' +
                ", scenario_writer='" + scenario_writer + '\'' +
                ", scenario_date='" + scenario_date + '\'' +
                ", project_id='" + project_id + '\'' +
                ", scenario_update='" + scenario_update + '\'' +
                '}';
    }
}
