package com.example.yoonlove.controller;

import com.example.yoonlove.dto.NoticeDto;
import com.example.yoonlove.dto.PageDto;
import com.example.yoonlove.dto.QnADto;
import com.example.yoonlove.service.CsService;
import com.example.yoonlove.service.PagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@Controller

public class CsController {

    @Autowired
    private CsService csService;

    @Autowired
    private PagingService pagingService;

    //공지사항
    @GetMapping("cs/selectnotice/{notice_id}")
    public ModelAndView selectNotice(NoticeDto noticedto){
        NoticeDto dto = csService.selectNotice(noticedto);

        /*조회수 증가메소드*/
        csService.NoticeCnt(noticedto);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("cs/selectnotice");
        mv.addObject("selectNotice", dto);

        return mv;
    }
    @GetMapping("/cs/notice")
    public ModelAndView selectListNotice(){
        List<NoticeDto> dto = csService.selectListNotice();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/cs/listnotice");
        mv.addObject("selectListNotice", dto);

        PageDto pageDto = new PageDto("qna","qna_id",1);

        PageDto test = pagingService.paging(pageDto);
        System.out.println(test.toString());

        return mv;
    }

    @GetMapping("/cs/insertnotice-view")
    public String insertNoticeView(){
        return "/cs/insertnoticeview";
    }

    @GetMapping("/cs/insertnotice")
    public String insertNotice(NoticeDto dto){
        csService.insertNotice(dto);
        return "/cs/notice";
    }

    @GetMapping("/cs/{notice_id}/updatenoticeview")
    public ModelAndView updateNoticeView(NoticeDto dto){
        NoticeDto noticedto = csService.selectNotice(dto);
        ModelAndView mv = new ModelAndView();
        mv.addObject("updateNotice", noticedto);
        mv.setViewName("/cs/updatenotice");
        return mv;
    }
    @GetMapping("/cs/{notice_id}/updatenotice")
    public String updateNotice(NoticeDto dto){
        csService.updateNotice(dto);
        return "/cs/notice";
    }

    @GetMapping("/cs/{notice_id}/deletenotice")
    public String deleteNotice(NoticeDto dto){
        csService.deleteNotice(dto);
        return "/cs/notice";
    }

    //QnA
    @GetMapping("/cs/selectqna/{qna_id}")
    public ModelAndView selectQnA(QnADto qnadto){
        QnADto dto = csService.selectQnA(qnadto);
        //조회수 증가 메소드
        csService.QnACnt(qnadto);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("/cs/selectqna");
        mv.addObject("selectQnA", dto);
        return mv;
    }
    @GetMapping("/cs/qna")
    public ModelAndView selectListQnA(){
        List<QnADto> dto = csService.selectListQnA();

        ModelAndView mv = new ModelAndView();
        mv.setViewName("/cs/listqna");
        mv.addObject("selectListQnA", dto);
        return mv;
    }

    @GetMapping("/cs/insertqnaview")
    public String insertQnAView(){
        return "/cs/insertqnaview";
    }
    @GetMapping("/cs/insertqna")
    public String insertQnA(QnADto dto){
        csService.insertQnA(dto);
        return "/cs/qna";
    }

    @GetMapping("/cs/{qna_id}/updateqnaview")
    public ModelAndView updateQnAView(QnADto qnadto){
        QnADto dto = csService.selectQnA(qnadto);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/cs/updateqna");
        mv.addObject("updateQnA",dto);
        return mv;
    }
    @GetMapping("/cs/{qna_id}/updateqna")
    public String updateQnA(QnADto dto){
        csService.updateQnA(dto);
        return "/cs/qna";
    }
    @GetMapping("/cs/{qna_id}/deleteqna")
    public String deleteQnA(QnADto dto){
        csService.deleteQnA(dto);

        return "/cs/qna";
    }
}
