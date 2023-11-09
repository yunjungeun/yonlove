package com.example.yoonlove.dto;

import lombok.Data;

@Data
public class VideoDto {
    private String video_id;
    private String video_name;
    private String video_sub;
    private int video_view;
    private int like_cnt;
    private String video_url;
    private String upload_date;  //타입을 date에서 string 으로 변경
    private int video_profit;
    private String project_id;
    private int commentcnt;

}