package com.example.yoonlove.controller;

import com.example.yoonlove.dto.ScriptPaperDto;
import com.example.yoonlove.dto.TimeTableDto;
import com.example.yoonlove.service.ScriptPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ScriptPaperController {
    @Autowired
    private ScriptPaperService scriptPaperService;

    //스크립트페이퍼
    @GetMapping("script/scriptpaper")
    public ModelAndView selectListScriptPaper(){
        List<ScriptPaperDto> dto = scriptPaperService.selectListScriptPaper();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("script/scriptpaper");
        mv.addObject("selectListScriptPaper", dto);
        return mv;
    }

    @GetMapping("script/selectscriptpaper")
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

    @GetMapping("script/updatescriptview")
    public ModelAndView updatescript(ScriptPaperDto scriptPaperDto){
        ScriptPaperDto dto = scriptPaperService.selectScriptPaper(scriptPaperDto);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("script/scriptupdate");
        mv.addObject("selectListScriptPaper", dto);
        return mv;
    }

    @GetMapping("script/updatescriptpaper")
    public String updateScriptPaper(ScriptPaperDto dto){
        scriptPaperService.updateScriptPaper(dto);
        return "redirect:/script/scriptpaper";
    }
    @GetMapping("script/deletescriptpaper")
    public String deleteScriptPaper(ScriptPaperDto dto){
        scriptPaperService.deleteScriptPaper(dto);
        return "redirect:/script/scriptpaper";
    }

    //타입테이블
    @GetMapping("script/timetable")
    public ModelAndView selectListTimeTable(){
        List<TimeTableDto> dto = scriptPaperService.selectListTimeTable();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("script/timtable");
        mv.addObject("selectListTimeTable", dto);
        return mv;
    }

    @GetMapping("script/selecttimetable")
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
        return "redirect:/script/timetable";
    }

    @GetMapping("script/updatetimeview")
    public ModelAndView updateTimeView(TimeTableDto timeTableDto){
        TimeTableDto dto = scriptPaperService.selectTimeTable(timeTableDto);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("script/timetableupdate");
        mv.addObject("selectTimeTable", dto);
        return mv;
    }
    @GetMapping("script/updatetimetable")
    public String updateTimeTable(TimeTableDto dto){
        scriptPaperService.updateTimeTable(dto);
        return "redirect:/script/timetable";
    }

    @GetMapping("script/deletetimetable")
    public String deleteTimeTable(TimeTableDto dto){
        scriptPaperService.deleteTimeTable(dto);
        return "redirect:/script/timetable";
    }
}
