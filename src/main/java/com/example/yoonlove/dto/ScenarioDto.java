package com.example.yoonlove.dto;

import lombok.Data;

import java.sql.Date;

@Data
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
    private String project_state;
    private Date project_start_date;  //값을 출력할때는 date타입, js로 넣어줄대는 string타입
    private String company_id;


}
