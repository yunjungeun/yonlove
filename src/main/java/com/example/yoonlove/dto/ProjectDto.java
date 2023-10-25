package com.example.yoonlove.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ProjectDto {
    //기획(프로젝트)
    private String project_id;  //기획아이디 1
    private String project_name;  //기획명2
    private int project_num;  // 기획번호3
    private String project_writer;  //작성자4
    private String project_concept;  //컨셉5
    private String project_content; // 기획내용6
    private String project_date;   //기획일7
    private Date project_start_date;  //제작시작일8
    private Date project_end_date;  //제작종료일9
    private String project_reg_date;  // 작성일10
    private String project_flag; // 종료여부11
    private String project_reason; //종료사유12
    private String company_id;  // 기업아이디13
    private String project_state;

    @Override
    public String toString() {
        return "ProjectDto{" +
                "project_id='" + project_id + '\'' +
                ", project_name='" + project_name + '\'' +
                ", project_number=" + project_num +
                ", project_writer='" + project_writer + '\'' +
                ", project_concept='" + project_concept + '\'' +
                ", project_content='" + project_content + '\'' +
                ", project_date=" + project_date +
                ", project_start_date=" + project_start_date +
                ", project_end_date=" + project_end_date +
                ", project_reg_date=" + project_reg_date +
                ", project_flag='" + project_flag + '\'' +
                ", project_reason='" + project_reason + '\'' +
                ", com_id='" + company_id + '\'' +
                '}';
    }
}
