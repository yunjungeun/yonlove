package com.example.yoonlove.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageDto {

    //외부에서 제공해줘야하는 값은...table, id는 외부에서 바꿔줘야함.
    //현재페이지는 항상 최신화 되어야함.
    //페이징의 최대 표시될 수의 값은 항상 최신화 되어야함.

    //페이징 처리하는 메소드의 Dto
    private int totalPost; //글의 총 갯수 값
    private int postStart; //게시판의 시작 게시글 번호
    private int postEnd; //게시판의 마지막 게시글 번호
    private int postCnt = 10; // 게시판에 보여주는 게시글의 수

    private int pageCnt; //페이지의 총 갯수 값
    private int currentPage = 1; //현재 페이지
    private int paging = 10; //게시글 밑 페이징에서 표시될 페이지의 수 값
    private int pageStart; //페에징에서 시작 페이지 값
    private int pageEnd; //페이징에서 마지막 값

    private String table;
    private String id;

    public PageDto(){}
    public PageDto(String table, String id, int currentPage){
        this.table = table;
        this.id = id;
        this.currentPage = currentPage;
    }

    @Override
    public String toString() {
        return "PageDto{" +
                "총 개시글의 수=" + totalPost +
                ", 현재페이지의 시작 글번호=" + postStart +
                ", 현재페이지의 끝 글번호=" + postEnd +
                ", 페이지당 표시되는 글의 수=" + postCnt +
                ", 페이지의 총 갯수=" + pageCnt +
                ", 현재페이지 값=" + currentPage +
                ", 페이지당 표시되는 글 의 수=" + paging +
                ", 페이징 시작 페이지=" + pageStart +
                ", 페이징 마지막 페이지=" + pageEnd +
                ", 테이블='" + table + '\'' +
                ", PK컬럼명='" + id + '\'' +
                '}';
    }
}
