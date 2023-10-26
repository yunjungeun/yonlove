package com.example.yoonlove.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CreatorDto {
    private String ch_id;
    private String ch_name;
    private int ch_sub;
    private String user_id;

     // 이어붙이는, 영상정보 테이블
    private String video_id;
    private String video_name;
    private String video_sub;
    private int video_view;
    private int like_cnt;
    private String video_url;
    private Date upload_date;
    private int video_profit;
    private String project_id;
    }

