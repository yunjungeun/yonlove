package com.example.yoonlove.controller;

import com.example.yoonlove.dto.PageDto;
import com.example.yoonlove.dto.UserDto;
import com.example.yoonlove.dto.VideoDto;
import com.example.yoonlove.service.PagingService;
import com.example.yoonlove.service.UserService;
import com.example.yoonlove.service.VideoService;
import com.example.yoonlove.service.YouTubeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
public class VideoController {
    @Autowired
    private VideoService videoService;
    @Autowired
    private PagingService pagingService;
    @Autowired
    private UserService userService;
    @Autowired
    private YouTubeService youTubeService;


      @GetMapping("vd/video") //컨텐츠 전체목록보기 뷰
    public ModelAndView selectListContent(PageDto pdto, @RequestParam(name="page", defaultValue = "1") int page,
                                          Principal user) {
        //유저정보 가저오는 dto
        UserDto userInfo = userService.getUser(user.getName());
        String companyId = userInfo.getCompany_id(); //회사 id 스트링

        PageDto pageDto = new PageDto("video","video_id", page,pdto, companyId);  // page???

        PageDto pageInfo = pagingService.paging(pageDto); // paging ==> 전체게시글 갯수 구해오는 메소드
        List<PageDto> pageList = pagingService.pageList(pageInfo.getPageStart(),pageInfo.getPageEnd(),page); // pageList==> 뷰페이지에 페이징 리스트를 생성해주는 리스트 메소드
        String rink = pagingService.pageRink(pageDto);

        //List<VideoDto> contentList = videoService.selectListContent(pageInfo);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("video/contentList");
        mv.addObject("selectContentList");

        mv.addObject("prefixUrl","vd");
        mv.addObject("paging", pageInfo);  //페이징정보
        mv.addObject("pagelist", pageList); //페이지 하단부 페이지 리스트
        mv.addObject("pageRink",rink); //검색유무에 다라 동적 페이지링크를 뷰페이지에 전달
        return mv;
    }

    @GetMapping("vd/content/{video_id}") //해당 컨텐츠 뷰
    public ModelAndView selectContent(VideoDto dto) {
        VideoDto contentDetail = videoService.selectContent(dto);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("/video/contentDetail");
        mv.addObject("content", contentDetail);
        return mv;
    }

    @GetMapping("vd/contentInsertView")  //컨텐츠 추가하는 뷰
    public ModelAndView selectVideoInsertView() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/video/contentInsertView");
        mv.setStatus(HttpStatus.valueOf(200));
        return mv;
    }

    @GetMapping("vd/contentInsert")  //컨텐츠 추가 처리
    @ResponseBody
    public String insertContent(VideoDto dto) {
        videoService.insertContent(dto);
        return "/vd/video";
    }

    @GetMapping("vd/{video_id}/UpdateView") //컨텐츠 업데이트하는 뷰
    public ModelAndView selectContentUpdateView( VideoDto dto) {
        VideoDto videodto = videoService.selectContent(dto);//업데이트를 하려면 해당 컨텐츠 불러와야하니까 위에 selectContent메소드를 다시씀!
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/video/contentUpdateView");
        mv.setStatus(HttpStatus.valueOf(200));
        mv.addObject("updateContent", videodto);
        return mv;
    }

    @GetMapping("vd/{video_id}/updateContent") //업데이트 처리
    @ResponseBody
    public String updateContent( VideoDto dto) {
        videoService.updateContent(dto);
        return "/vd/video";
    }

    @GetMapping("/vd/{video_id}/delete") //삭제 처리
    public String deleteContent( VideoDto dto) {
        videoService.deleteContent(dto);
        return "redirect:/vd/video";

    }
}