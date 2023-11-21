package com.example.yoonlove.dto;

import lombok.Data;

@Data
public class DepartmentDto {
    private String dpt_id;
    private String dpt_name;
    private String dpt_function;
    private String company_id;

    //조인(company테이블) 프로퍼티 / 테이블 : departmentjoin
    private String company_name;
}
