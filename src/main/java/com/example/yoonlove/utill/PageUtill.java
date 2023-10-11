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
    public int totalPageCnt(){
        PageDto totalPage = new PageDto();

        //(전체글 -1) / 페이징 갯수   + 1
        int pageCnt =(totalPost()-1)/totalPage.getPaging() + 1;

        return pageCnt;
    }

    //현재 페이징에서 시작 페이지 구하는 법 // (현재페이지 번호 -1 / 페이징사이즈)* 페이징 사이즈 + 1 =시작번호
    //현재페이지값은 setter나 객체로 넣고, 기본값은 0임. 페이징사이즈 기본값은 10임.
    public int pageStart(){
        PageDto pageDto = new PageDto();
        int result = ((pageDto.getCurrentPage() - 1) / pageDto.getPaging()) * pageDto.getPaging() + 1;
        return result;
    }

    //페이징에서 끝 페이지 구하는법 // 시작페이지 + 페이징 사이즈 - 1 과 총페이지보다 작은것
    public int pageEnd(){
        PageDto dto = new PageDto();
        int result = pageStart() + dto.getPaging() - 1;
        return Math.min(result, totalPost());
    }
    //게시판의 마지막 글 번호를 구하는 메소드
    // 현재페이지 * 페이지당 글 수 = 페이지의 마지막글 번호
    public int postEnd(){
        PageDto dto = new PageDto();
        int result = dto.getCurrentPage()*dto.getPaging();
        //여기도 총 글자숫자보다 작으면 비교하는 값이 잇어야함.
        return result;
    }
    //게시판의 시작 글 번호 구하는 메소드
    // 마지막 글번호 - (페이지당 글 수-1) = 시작 글번호
    // 마지막 글번호가 15라면 문제가 될수도있음. -->페이지 당 글 개수가 최신화 되야함.
    public int postStart(){
        PageDto dto = new PageDto();
        int result = postEnd() - (dto.getPaging() - 1);
        return result;
    }
    //'이전' 단추를 구하는 방법
    // 페이징의 시작 페이지가 1이면 참 아니면 거짓 산출
    public boolean pre(){
        return pageStart() != 1;
    }
    public boolean next(){
        return pageEnd() < totalPageCnt();
    }
}
