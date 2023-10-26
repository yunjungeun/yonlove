package com.example.yoonlove.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class VideoDto {
    private String video_id;
    private String video_name;
    private String video_sub;
    private int video_view;
    private int like_cnt;
    private String video_url;
    private Date upload_date;
    private int video_profit;
    private String project_id;

    @Override
    public String toString() {
        return "VideoDto{" +
                "video_id='" + video_id + '\'' +
                ", video_name='" + video_name + '\'' +
                ", video_sub='" + video_sub + '\'' +
                ", video_view=" + video_view +
                ", like_cnt=" + like_cnt +
                ", video_url='" + video_url + '\'' +
                ", upload_date=" + upload_date +
                ", video_profit=" + video_profit +
                ", project_id='" + project_id + '\'' +
                '}';
    }
}