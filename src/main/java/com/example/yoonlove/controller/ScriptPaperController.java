package com.example.yoonlove.controller;

import com.example.yoonlove.dto.PageDto;
import com.example.yoonlove.dto.ScriptPaperDto;
import com.example.yoonlove.dto.TimeTableDto;
import com.example.yoonlove.service.PagingService;
import com.example.yoonlove.service.ScriptPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ScriptPaperController {
    @Autowired
    private ScriptPaperService scriptPaperService;
    @Autowired
    private PagingService pagingService;

    //스크립트페이퍼
    @GetMapping("script/scriptpaper")
    public ModelAndView selectListScriptPaper(PageDto pdto, @RequestParam(name="page", defaultValue = "1") int page){
        PageDto pageDto = new PageDto("scriptpaper","script_id", page,pdto);
        PageDto pageInfo = pagingService.paging(pageDto);
        List<PageDto> pageList = pagingService.pageList(pageInfo.getPageStart(),pageInfo.getPageEnd(),page);
        String rink = pagingService.pageRink(pageDto);

        List<ScriptPaperDto> dto = scriptPaperService.selectListScriptPaper(pageInfo);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("script/script");
        mv.addObject("selectListScriptPaper", dto);

        //페이징에 필요한센션
        mv.addObject("paging", pageInfo);  //페이징정보
        mv.addObject("pagelist", pageList); //페이지 하단부 페이지 리스트
        mv.addObject("pageRink",rink); //검색유무에 다라 동적 페이지링크를 뷰페이지에 전달

        return mv;
    }

    @GetMapping("script/{script_id}/selectscriptpaper")
    public ModelAndView selectScriptPaper(ScriptPaperDto scriptPaperDto){
        ScriptPaperDto dto = scriptPaperService.selectScriptPaper(scriptPaperDto);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/script/scriptselect");
        mv.addObject("selectScriptPaper", dto);
        return mv;
    }

    @GetMapping("script/insertscriptpaperview")
    public String insertscript(){
        return "script/scriptinsert";
    }

    @GetMapping("script/insertscriptpaper")
    public String insertScriptPaper(ScriptPaperDto dto){
        scriptPaperService.insertScriptPaper(dto);
        return "redirect:/script/scriptpaper";
    }

    @GetMapping("script/{script_id}/updatescriptview")
    public ModelAndView updatescript(ScriptPaperDto scriptPaperDto){
        ScriptPaperDto dto = scriptPaperService.selectScriptPaper(scriptPaperDto);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("script/scriptupdate");
        mv.addObject("selectScriptPaper", dto);
        return mv;
    }

    @GetMapping("script/{script_id}/updatescriptpaper")
    public String updateScriptPaper(ScriptPaperDto dto){
        scriptPaperService.updateScriptPaper(dto);
        return "redirect:/script/scriptpaper";
    }
    @GetMapping("script/{script_id}/deletescriptpaper")
    public String deleteScriptPaper(ScriptPaperDto dto){
        scriptPaperService.deleteScriptPaper(dto);
        return "redirect:/script/scriptpaper";
    }

    //타입테이블
    @GetMapping("script/timetable")
    public ModelAndView selectListTimeTable(PageDto pdto, @RequestParam(name="page", defaultValue = "1") int page){
        PageDto pageDto = new PageDto("timetable","table_id",page,pdto);
        PageDto pageInfo = pagingService.paging(pageDto);
        List<PageDto> pageList =pagingService.pageList(pageInfo.getPageStart(), pageInfo.getPageEnd(),page);
        String rink = pagingService.pageRink(pageDto);

        List<TimeTableDto> dto = scriptPaperService.selectListTimeTable(pageInfo);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("script/timetable");
        mv.addObject("selectListTimeTable", dto);

        //페이징에 필요한센션
        mv.addObject("paging", pageInfo);  //페이징정보
        mv.addObject("pagelist", pageList); //페이지 하단부 페이지 리스트
        mv.addObject("pageRink",rink); //검색유무에 다라 동적 페이지링크를 뷰페이지에 전달

        return mv;
    }

    @GetMapping("script/{table_id}/selecttimetable")
    public ModelAndView selectTimeTable(TimeTableDto timetableDto){
        TimeTableDto dto = scriptPaperService.selectTimeTable(timetableDto);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("script/timetableselect");
        mv.addObject("selectTimeTable", dto);
        return mv;
    }

    @GetMapping("script/inserttimeview")
    public String insertTimeView(){
        return "script/timetableinsert";
    }

    @GetMapping("script/inserttimetable")
    public String insertTimeTable(TimeTableDto dto){
        scriptPaperService.insertTimeTable(dto);
        return "redirect:timetable";
    }

    @GetMapping("script/{table_id}/updatetimeview")
    public ModelAndView updateTimeView(TimeTableDto timeTableDto){
        TimeTableDto dto = scriptPaperService.selectTimeTable(timeTableDto);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("script/timetableupdate");
        mv.addObject("selectTimeTable", dto);
        return mv;
    }
    @GetMapping("script/{table_id}/updatetimetable")
    public String updateTimeTable(TimeTableDto dto){
        System.out.println(dto.toString());
        scriptPaperService.updateTimeTable(dto);
        return "redirect:/script/timetable";
    }

    @GetMapping("script/{table_id}/deletetimetable")
    public String deleteTimeTable(TimeTableDto dto){
        scriptPaperService.deleteTimeTable(dto);
        return "redirect:/script/timetable";
    }
}
