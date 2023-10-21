package com.example.yoonlove.dto;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class NoticeDto {
    private int notice_id;
    private String notice_title;
    private String notice_content;
    private String notice_writer;
    private String notice_date;
    private String notice_update;
    private int notice_cnt;
    private String user_id;


    @Override
    public String toString() {
        return "NoticeDto{" +
                "notice_id=" + notice_id +
                ", notice_title='" + notice_title + '\'' +
                ", notice_content='" + notice_content + '\'' +
                ", notice_writer='" + notice_writer + '\'' +
                ", notice_date='" + notice_date + '\'' +
                ", notice_update='" + notice_update + '\'' +
                ", notice_cnt=" + notice_cnt +
                ", user_id='" + user_id + '\'' +
                '}';
    }
}
