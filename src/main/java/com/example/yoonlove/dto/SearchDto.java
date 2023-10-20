package com.example.yoonlove.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class SearchDto {
    private String id;
    private String intid;
    private String writer;
    private String content;
    private String title;
    private Date date;

    private String search;
}
