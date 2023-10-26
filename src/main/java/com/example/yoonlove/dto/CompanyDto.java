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
    private String company_address1;
    private String company_address2;
    private String company_address3;
    private String company_email;
    private String zip_code;

    @Override
    public String toString() {
        return "CompanyDto{" +
                "company_id='" + company_id + '\'' +
                ", company_name='" + company_name + '\'' +
                ", company_date=" + company_date +
                ", company_homepage='" + company_homepage + '\'' +
                ", company_address1='" + company_address1 + '\'' +
                ", company_address2='" + company_address2 + '\'' +
                ", company_address3='" + company_address3 + '\'' +
                ", company_email='" + company_email + '\'' +
                ", zip_code='" + zip_code + '\'' +
                '}';
    }
}
