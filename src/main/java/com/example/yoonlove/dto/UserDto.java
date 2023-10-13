package com.example.yoonlove.dto;

import lombok.Data;

import java.sql.Date;


@Data
public class UserDto {

    private boolean autoLogin;
    private String sessionId;
    private Date expiredAt;

    private String user_id;
    private String pw;
    private String pw2;
    private String user_name;
    private String nickname;
    private int phone;
    private int tell;
    private String email;
    private String address1;
    private String address2;
    private int zipcode;
    private String company_user;
    private String grade;
    private String authority;
    private String company_id;
    private String dpt_id;
    private Date sign_date;
    private int basic_pay;
    private int total_pay;






}

