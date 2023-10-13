package com.example.yoonlove.service;

import com.example.yoonlove.dto.PageDto;
import com.example.yoonlove.mapper.PageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PagingService {

    @Autowired
    private PageMapper pageMapper;


    public PageDto paging(PageDto dto){
        PageDto pageDto = new PageDto();
    //dto 리턴값 = 전체게시글 구하는 sql 실행 메소드
        pageDto = pageMapper.totalPost(dto);
        int totalPost = pageDto.getTotalPost();

        return pageDto;
    }



    //전체 글의 갯수를 구하는 메소드
    public int totalPost(PageDto dto){
        //dto 리턴값     = 전체게시글 구하는 sql 실행 메소드
        PageDto pageDto = pageMapper.totalPost(dto);
    //전체글 수 값(정수) = pageDto객체에 저장된 전체 게시글의 수 값
        int totalPost = pageDto.getTotalPost();

        return totalPost;
    }

    public int totalPage(PageDto dto){
        // 전체페이지  =     (전체글 -1) / 페이징 갯수   + 1
        int totalPage = (totalPost(dto)-1) / dto.getPaging() + 1;
        return totalPage;
    }


    //현재페이지값은 setter나 객체로 넣고, 기본값은 0임. 페이징사이즈 기본값은 10임.
    public int pageStart(PageDto dto){
        //시작페이지 //     (현재페이지 번호        -1   / 페이징사이즈)      * 페이징 사이즈     + 1
        int pageStart = ((dto.getCurrentPage() - 1) / dto.getPaging()) * dto.getPaging() + 1;
        return pageStart;
    }

    //페이징에서 끝 페이지 구하는법 // 시작페이지 + 페이징 사이즈 - 1 과 총페이지보다 작은것
    public int pageEnd(PageDto dto){
        //시작페이지 구하는 식
        int pageStart = ((dto.getCurrentPage() - 1) / dto.getPaging()) * dto.getPaging() + 1;
        //끝페이지  = 시작페이지  + 페이징 사이즈     -1
        int result = pageStart + dto.getPaging() - 1;
        return Math.min(result, totalPage(dto));
    }


    //게시판의 마지막 글 번호를 구하는 메소드
    // 현재페이지 * 페이지당 글 수 = 페이지의 마지막글 번호
    public int postEnd(PageDto dto){
        //마지막글번호 =  현재페이지         *  페이지당 글 수
        int postEndNum = dto.getCurrentPage()*dto.getPostCnt();
        //여기도 총 글자숫자보다 작으면 비교하는 값이 잇어야함.
        return Math.min(postEndNum,totalPost(dto));
    }
    //게시판의 시작 글 번호 구하는 메소드
    // 마지막 글번호 - (페이지당 글 수-1) = 시작 글번호
    // 마지막 글번호가 15라면 문제가 될수도있음. -->페이지 당 글 개수가 최신화 되야함.
    public int postStart(PageDto dto){
        int postStartNum = postEnd(dto) - (dto.getPostCnt() - 1);
        return postStartNum;
    }

}
