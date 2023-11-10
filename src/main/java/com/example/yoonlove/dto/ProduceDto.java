package com.example.yoonlove.dto;

import lombok.Data;

@Data
public class ProduceDto {
    //제작인원편성
    private String pd_id;
    private String pd_name;
    private String role;
    private String casting;
    private String project_id;
    private String user_id;

    // 조인테이블 : producejoin
    private String company_id;
    private String project_name;

}
