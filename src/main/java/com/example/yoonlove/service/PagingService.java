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

        //전체 게시글의 갯수 값
        int totalPost = pageDto.getTotalPost();
        //전체 페이지의 갯수 값
        int totalPage = (totalPost-1) / dto.getPaging() + 1;
        pageDto.setPageCnt(totalPage);

        //시작페이지 //     (현재페이지 번호        -1   / 페이징사이즈)      * 페이징 사이즈     + 1
        int pageStart = ((dto.getCurrentPage() - 1) / dto.getPaging()) * dto.getPaging() + 1;
        pageDto.setPageStart(pageStart);

        //끝페이지  =             (시작페이지  + 페이징 사이즈     -1)와 총페이지 중 작은것
        int pageEnd = Math.min((pageStart + dto.getPaging() - 1),totalPage);
        pageDto.setPageEnd(pageEnd);

        //  마지막글번호 =             (현재페이지         *  페이지당 글 수 )와 전체글 중 작은 값
        int postEndNum = Math.min((dto.getCurrentPage()*dto.getPostCnt()),totalPost);
        pageDto.setPostEnd(postEndNum);
        //  시작글 번호   =  마지막 글버놓 - (  페이지당 글수   - 1)
        int postStartNum = postEndNum - (dto.getPostCnt() - 1);
        pageDto.setPostStart(postStartNum);

        return pageDto;
    }

}
