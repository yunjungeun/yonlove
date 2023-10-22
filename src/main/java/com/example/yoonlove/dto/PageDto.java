package com.example.yoonlove.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

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
    private int postCnt = 1; // 게시판에 보여주는 게시글의 수

    private int pageCnt; //페이지의 총 갯수 값
    private int currentPage = 1; //현재 페이지
    private boolean currentPageFlag; //페이지리스트 중에서 반복문 페이지가 현재페이지와 일치하는지 확인  현재페이지 true (굵은글씨체 ) 현재페이지가 아니면 flase..

    private int paging = 10; //게시글 밑 페이징에서 표시될 페이지의 수 값
    private int pageStart; //페에징에서 시작 페이지 값
    private int pageEnd; //페이징에서 마지막 값

    //이전 / 다음 존재유무에 대한 변수값
    private boolean hasPre;
    private boolean hasNext;

    private int prePage;  //이전페이지 값
    private int nextPage;  //다음페이지 값


    private String table;    /// ???????
    private String id;

    //검색관련 프로퍼티
    private String pkid;
    private String pkintid;
    private String writer;
    private String content;
    private String title;
    private Date date;

    private String search;



    public PageDto(){}
    public PageDto(String table, String pkid, int currentPage, PageDto dto){ //PageDto dto ==> 얘는 검색떄문에 넣은거임
        this.table = table;
        this.id = pkid;
        this.currentPage = currentPage;
        this.pkid = dto.getPkid();
        this.pkintid = dto.getPkintid();
        this.writer = dto.getWriter();
        this.content = dto.getContent();
        this.title = dto.getTitle();
        this.date = dto.getDate();
        this.search = dto.getSearch();
    }
    public PageDto(int currentPage, boolean currentPageFlag){
        this.currentPage = currentPage;
        this.currentPageFlag = currentPageFlag;
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