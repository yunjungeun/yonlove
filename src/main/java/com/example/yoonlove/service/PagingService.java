package com.example.yoonlove.service;

import com.example.yoonlove.dto.PageDto;
import com.example.yoonlove.mapper.PageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PagingService {

    @Autowired
    private PageMapper pageMapper;

    //전체 글의 갯수를 구하는 메소드
    public PageDto totalPost(PageDto dto){
        return pageMapper.totalPost(dto);
    }


    //총페이지를 구하는 법=
    // (전체글 -1) / 페이징 갯수   + 1

    //총 페이지 구하는법
/*    public int totalPageCnt(PageDto dto){
        PageDto result = new PageDto();
        int cnt = (dto.getCurrentPage() - 1 / dto.getPaging())+1;
        result.setPageCnt(cnt);
        return cnt;
    }*/

}
