package com.example.yoonlove.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class PlanDto {

    private int day_id;
    private Date film_date;
    private String weather;
    private String light;
    private String costume;
    private String prop;
    private int film_order;
    private String direct;
    private int project_id;

}