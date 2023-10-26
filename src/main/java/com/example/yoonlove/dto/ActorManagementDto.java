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



    private String project_name;  //기획명2
    private int project_num;  // 기획번호3
    private String project_writer;  //작성자4
    private String project_concept;  //컨셉5
    private String project_content; // 기획내용6
    private String project_date;   //기획일7
    private String project_start_date;  //제작시작일8
    private String project_end_date;  //제작종료일9
    private String project_reg_date;  // 작성일10
    private String project_flag; // 종료여부11
    private String project_reason; //종료사유12
    private String company_id;  // 기업아이디13
    private String project_state;
}