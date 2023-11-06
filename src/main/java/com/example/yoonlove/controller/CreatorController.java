package com.example.yoonlove.controller;

import com.example.yoonlove.dto.CreatorDto;
import com.example.yoonlove.dto.PageDto;
import com.example.yoonlove.service.CreatorService;
import com.example.yoonlove.service.PagingService;
import com.example.yoonlove.service.YouTubeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
    public class CreatorController {

        @Autowired
        private CreatorService creatorservice;

        @Autowired
        private PagingService pagingService;
        @Autowired
        private YouTubeService youTubeService;


        @GetMapping("creator/creater")  // 뒤에 테이블명
        public ModelAndView selectListCreaotr(PageDto pdto, @RequestParam(name="page", defaultValue = "1") int page){
            PageDto pageDto = new PageDto("creater", "ch_id", page, pdto);
            PageDto pageInfo=pagingService.paging(pageDto);
            List<PageDto> pageList = pagingService.pageList(pageInfo.getPageStart(), pageInfo.getPageEnd(), page);
            String rink = pagingService.pageRink(pageDto);

            List<CreatorDto> dto = creatorservice.selectListCreator(pageInfo);

            DecimalFormat decimalFormat = new DecimalFormat("#,###");
            for(int i = 0; i<dto.size(); i++){
                String formattedVideoCount = decimalFormat.format(dto.get(i).getVideocount());
                String formattedCh_sub =  decimalFormat.format(dto.get(i).getCh_sub());
                dto.get(i).setFormattedVideocount(formattedVideoCount);
                dto.get(i).setFormattedCh_sub(formattedCh_sub);
            }

            ModelAndView mv = new ModelAndView();
            mv.setViewName("creator/creator");
            mv.addObject("selectListCreator", dto);



            //페이징에 필요한센션
            mv.addObject("prefixUrl", "creator"); // 컨트롤러 앞부분 /명
            mv.addObject("paging", pageInfo);  //페이징정보
            mv.addObject("pagelist", pageList); //페이지 하단부 페이지 리스트
            mv.addObject("pageRink",rink); //검색유무에 다라 동적 페이지링크를 뷰페이지에 전달
            return mv;
        }

        @GetMapping("creator/insertcreator")
        public String insertcreatorview(String userId, String channelId, String title) throws GeneralSecurityException, IOException {
            CreatorDto dto = youTubeService.channel(channelId);
            dto.setCh_name(title);
            dto.setUser_id(userId);
            creatorservice.insertCreator(dto);

            return "creator/creatorinsert";
        }


      @GetMapping("/creator/{ch_id}/selectcreator")
      public ModelAndView selectCreator(CreatorDto creatorDto){
          CreatorDto dto = creatorservice.selectCreator(creatorDto);
          ModelAndView mv = new ModelAndView();

          //dto의 조회수, 영상수, 구독자수를 쉼표를 포함한 문자열로 변환
          DecimalFormat decimalFormat = new DecimalFormat("#,###");
          String formattedVideoCount = decimalFormat.format(dto.getVideocount());
          String formattedViewCount = decimalFormat.format(dto.getViewcount());
          String formattedCh_sub =  decimalFormat.format(dto.getCh_sub());

          //변환값을 dto에 바인드
          dto.setFormattedVideocount(formattedVideoCount);
          dto.setFormattedViewcount(formattedViewCount);
          dto.setFormattedCh_sub(formattedCh_sub);

          mv.setViewName("/creator/selectcreator");
          mv.addObject("selectCreator", dto);
          // 기존 크레이터 값 끝
          return mv;
      }

    @GetMapping("/creator/updatecreatorview/{ch_id}") // 수정하는곳
    public ModelAndView updateCreatorView(CreatorDto creatorDto){
        ModelAndView mv = new ModelAndView();
        CreatorDto dto = creatorservice.selectCreator(creatorDto);
        mv.setViewName("/creator/updatecreator");
        mv.addObject("selectCreator", dto);
        return mv;
    }

     @GetMapping("/creator/updatecreator/{ch_id}") // 수정
     @ResponseBody
    public String updateCreator(CreatorDto dto){
            /*System.out.println(dto.toString());*/
        creatorservice.updateCreator(dto);
        return "/creator/creater";   // 목록페이지로 이동
    }

    @GetMapping("/creator/deletecreator/{ch_id}")
    public String deleteCreator(CreatorDto dto){
        creatorservice.deleteCreator(dto);
        return "redirect:/creator/creater";
    }

    //검색어로 채널 리스트 검색
    @PostMapping("/creator/search")
    public ModelAndView searchChannel(String searchKeyword, String userId) throws GeneralSecurityException, IOException {
        ArrayList<HashMap<String, String>> result = youTubeService.searchId(searchKeyword);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/creator/creatorinsert");
        mv.addObject("userId", userId);
        mv.addObject("hashmap", result);
        mv.addObject("openInNewTab", true);
        return mv;
    }
}
