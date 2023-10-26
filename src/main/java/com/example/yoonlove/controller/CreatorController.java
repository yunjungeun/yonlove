package com.example.yoonlove.controller;

import com.example.yoonlove.dto.CreatorDto;
import com.example.yoonlove.dto.PageDto;
import com.example.yoonlove.service.CreatorService;
import com.example.yoonlove.service.PagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
    public class CreatorController {

        @Autowired
        private CreatorService creatorservice;

        @Autowired
        private PagingService pagingService;


        @GetMapping("creator/creater")  // 뒤에 테이블명
        public ModelAndView selectListCreaotr(PageDto pdto, @RequestParam(name="page", defaultValue = "1") int page){
            PageDto pageDto = new PageDto("creater", "ch_id", page, pdto);
            PageDto pageInfo=pagingService.paging(pageDto);
            List<PageDto> pageList = pagingService.pageList(pageInfo.getPageStart(), pageInfo.getPageEnd(), page);
            String rink = pagingService.pageRink(pageDto);

            List<CreatorDto> dto = creatorservice.selectListCreator(pageInfo);
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

        @GetMapping("creator/insertcreatorview")
        public String insertcreatorview(){
            return "creator/creatorinsert";
        }

        @GetMapping("creator/insertcreator")
        public String insertCreator(CreatorDto creatorDto){
            creatorservice.insertCreator(creatorDto);
            return "redirect:/creator/creater";
        }

      @GetMapping("/creator/{ch_id}/selectcreator")
      public ModelAndView selectCreator(CreatorDto creatorDto){
          CreatorDto dto = creatorservice.selectCreator(creatorDto);
          ModelAndView mv = new ModelAndView();
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
    public String updateCreator(CreatorDto dto){
            /*System.out.println(dto.toString());*/
        creatorservice.updateCreator(dto);
        return "redirect:/creator/creater";   // 목록페이지로 이동
    }

    @GetMapping("/creator/deletecreator/{ch_id}")
    public String deleteCreator(CreatorDto dto){
        creatorservice.deleteCreator(dto);
        return "redirect:/creator/creater";
    }
}
