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

    //조인테이블 프로퍼티 /조인 뷰테이블명 : scenariolist
    private String project_name;
    private int project_num;
    private String project_writer;
    private String project_concept;
    private String project_flag;
    private String project_date;

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
