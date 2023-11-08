package com.example.yoonlove.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter

public class LogDto {
    private String log_id;
    private String log_num;
    private Date log_date;
    private String log_content;
    private String project_id;
    private String project_name;  // logjoin 뷰 테이블에서 project_name 추가 후 dto 도 추가!!

    //조인테이블 : logjoin
    private String company_id;

    @Override
    public String toString() {
        return "LogDto{" +
                "log_id='" + log_id + '\'' +
                ", log_num='" + log_num + '\'' +
                ", log_date=" + log_date +
                ", log_content='" + log_content + '\'' +
                ", project_id='" + project_id + '\'' +
                ", project_name='" + project_name + '\'' +
                ", company_id='" + company_id + '\'' +
                '}';
    }
}