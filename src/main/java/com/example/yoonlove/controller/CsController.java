package com.example.yoonlove.controller;

import com.example.yoonlove.dto.NoticeDto;
import com.example.yoonlove.dto.PageDto;
import com.example.yoonlove.dto.QnADto;
import com.example.yoonlove.service.CsService;
import com.example.yoonlove.service.PagingService;
import com.example.yoonlove.service.UserService;
import com.example.yoonlove.service.YouTubeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;
@Controller

public class CsController {

    @Autowired
    private CsService csService;
    @Autowired
    private UserService userService;
    @Autowired
    private PagingService pagingService;
    private final YouTubeService youTubeService;

    @Autowired
    public CsController(YouTubeService youTubeService) {
        this.youTubeService = youTubeService;
    }

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
    public ModelAndView selectListNotice(PageDto pdto,@RequestParam(name="page", defaultValue = "1") int page){
        //페이징에 필요한 매개변수, 객체생성
        PageDto pageDto = new PageDto("notice","notice_id",page, pdto);

        //페이징정보처리 메소드
        PageDto pageInfo = pagingService.paging(pageDto);


        //뷰페이지에 하단 페이징처리를 해주는 리스트
        List<PageDto> pagelist = pagingService.pageList(pageInfo.getPageStart(), pageInfo.getPageEnd(), page);

        //검색유무에 따라 동적 페이지링크를 만들어줌
        String rink = pagingService.pageRink(pageDto);

        List<NoticeDto> dto = csService.selectListNotice(pageInfo);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/cs/listnotice");
        mv.addObject("selectListNotice", dto);


        //페이징에 필요한센션
        mv.addObject("prefixUrl", "cs");
        mv.addObject("paging", pageInfo);  //페이징정보
        mv.addObject("pagelist", pagelist); //페이지 하단부 페이지 리스트
        mv.addObject("pageRink",rink); //검색유무에 다라 동적 페이지링크를 뷰페이지에 전달


       //유튜브 api 부분
     /*   try {
            System.out.println(youTubeService.searchVideos("ytn"));
        } catch (IOException e) {
            e.printStackTrace();
            return mv;
        }

        try {
            youTubeService.searchCh();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
        //유튜브 api 끝

        return mv;
    }

    @GetMapping("/cs/insertnotice-view")
    public ModelAndView insertNoticeView(Principal user){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/cs/insertnoticeview");
        mv.addObject("writer", userService.getnick(user));
        return mv;
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
        System.out.println(dto.toString());
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
    public ModelAndView selectListQnA(PageDto pdto,@RequestParam(name="page", defaultValue = "1") int page){
        System.out.println("질의응답 게시판리스트");
        PageDto pageDto = new PageDto("qna","qna_id",page,pdto);
        PageDto pageInfo = pagingService.paging(pageDto);

        List<PageDto> pagelist = pagingService.pageList(pageInfo.getPageStart(),pageInfo.getPageEnd(),page);
        String rink = pagingService.pageRink(pageDto);

        List<QnADto> pagedto = csService.selectListQnA(pageInfo);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/cs/listqna");
        mv.addObject("selectListQnA", pagedto);


        //페이징에 필요한센션
        mv.addObject("prefixUrl", "cs");
        mv.addObject("paging", pageInfo);  //페이징정보
        mv.addObject("pagelist", pagelist); //페이지 하단부 페이지 리스트
        mv.addObject("pageRink",rink); //검색유무에 다라 동적 페이지링크를 뷰페이지에 전달
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
