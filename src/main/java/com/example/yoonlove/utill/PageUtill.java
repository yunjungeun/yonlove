package com.example.yoonlove.utill;

import com.example.yoonlove.dto.PageDto;
import com.example.yoonlove.service.PagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PageUtill {

    @Autowired
    private PagingService pagingService;

    //글의 총 갯수를 구하는 메서드
    public int totalPost(){
        PageDto cnt = new PageDto();
        PageDto result = pagingService.totalPost(cnt);
        return result.getTotalPost();
    }

    //총 페이지를 구하는 방법  = // (전체글 -1) / 페이징 갯수   + 1
    public int totalPageCnt(PageDto dto){
        PageDto totalPage = new PageDto();

        //(전체글 -1) / 페이징 갯수   + 1
        int pageCnt =(totalPost()-1)/totalPage.getPageCnt() + 1;

        return pageCnt;
    }
}
