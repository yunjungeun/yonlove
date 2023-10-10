package com.example.yoonlove.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class CompanyDto {
    private String company_id;
    private String company_name;
    private Date company_date;
    private String company_homepage;
    private String company_adress1;
    private String company__adress2;
    private String company_email;
}
