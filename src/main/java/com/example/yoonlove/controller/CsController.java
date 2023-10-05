package com.example.yoonlove.controller;

import com.example.yoonlove.dto.NoticeDto;
import com.example.yoonlove.dto.QnADto;
import com.example.yoonlove.service.CsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CsController {

    @Autowired
    private CsService csService;

    //공지사항
    @GetMapping("/cs/selectnotice")
    public ModelAndView selectNotice(NoticeDto noticedto){
        NoticeDto dto = csService.selectNotice(noticedto);
        System.out.println("확인점1");
        csService.cnt();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/cs/selectnotice");
        mv.addObject("selectNotice", dto);
        return mv;
    }
    @GetMapping("/cs/notice")
    public ModelAndView selectListNotice(){
        List<NoticeDto> dto = csService.selectListNotice();

        ModelAndView mv = new ModelAndView();
        mv.setViewName("/cs/listnotice");
        mv.addObject("selectListNotice", dto);
        return mv;
    }

    @GetMapping("/cs/insertnotice-view")
    public ModelAndView insertNoticeView(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/cs/insertnoticeview");
        return mv;
    }

    @GetMapping("/cs/insertnotice")
    public String insertNotice(NoticeDto dto){
        csService.insertNotice(dto);

        return "/cs/notice";
    }

    @GetMapping("/cs/updatenoticeview")
    public String updateNoticeView(){
/*        ModelAndView mv = new ModelAndView();
        mv.addObject("updateNoticeView", dto);
        mv.setViewName("/cs/updatenotice");
        return mv;*/
        return "updatenotice";
    }

    @GetMapping("/cs/updatenotice")
    public String updateNotice(NoticeDto dto){
        csService.updateNotice(dto);

        return "/cs/notice";
    }
    @GetMapping("/cs/deletenotice")
    public ModelAndView deleteNotice(){
        csService.deleteNotice();

        ModelAndView mv = new ModelAndView();
        mv.setViewName("/cs/selectlistnotice");
        return mv;
    }

    //QnA
    @GetMapping("/cs/selectqna")
    public ModelAndView selectQnA(){
        QnADto dto = csService.selectQnA();

        ModelAndView mv = new ModelAndView();
        mv.setViewName("/cs/selectqna");
        mv.addObject("selectQnA", dto);
        return mv;
    }
    @GetMapping("/cs/qna")
    public ModelAndView selectListQnA(){
        List<QnADto> dto = csService.selectListQnA();

        ModelAndView mv = new ModelAndView();
        mv.setViewName("/cs/selectlistqna");
        mv.addObject("selectListQnA", dto);
        return mv;
    }
    @GetMapping("/cs/insertqna")
    public ModelAndView insertQnA(){
        csService.insertQnA();

        ModelAndView mv = new ModelAndView();
        mv.setViewName("/cs/selectlistqna");
        return mv;
    }
    @GetMapping("/cs/updateqna")
    public ModelAndView updateQnA(){
        csService.updateQnA();

        ModelAndView mv = new ModelAndView();
        mv.setViewName("/cs/selectqna");
        return mv;
    }
    @GetMapping("/cs/deleteqna")
    public ModelAndView deleteQnA(){
        csService.deleteQnA();

        ModelAndView mv = new ModelAndView();
        mv.setViewName("/cs/selectlistqna");
        return mv;
    }
}
