package com.example.yoonlove.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageDto {

    //페이징 처리하는 메소드의 Dto

    private int totalPost; //글의 총 갯수 값
    private int postStart; //게시판의 시작 게시글 번호
    private int postEnd; //게시판의 마지막 게시글 번호

    private int pageCnt; //페이지의 총 갯수 값
    private int currentPage; //현재 페이지
    private int paging; //게시글 밑 페이징에서 표시될 페이지의 수 값
    private int pageStart; //페에징에서 시작 페이지 값
    private int pageEnd; //페이징에서 마지막 값


    private String table = "notice";
    private String id = "notice_id";

    @Override
    public String toString() {
        return "PageDto{" +
                "table='" + table + '\'' +
                ", id='" + id + '\'' +
                ", totalPost='" + totalPost + '\'' +
                '}';
    }
}
