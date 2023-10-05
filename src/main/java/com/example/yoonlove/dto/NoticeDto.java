package com.example.yoonlove.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
@Getter
@Setter
public class NoticeDto {
    private int notice_id;
    private String notice_title;
    private String notice_content;
    private String notice_writer;
    private String notice_date;
    private Date notice_update;
    private int notice_cnt;
    private String user_id;
}
