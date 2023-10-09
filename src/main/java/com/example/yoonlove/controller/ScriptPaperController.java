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
    @GetMapping("/listtimetable")
    public ModelAndView selectListTimeTable(){
        //실행할 메소드(서비스 부분에 있는 메소드)
        List<TimeTableDto> dto = scriptPaperService.selectListTimeTable();

        //세션 객체생셩
        ModelAndView mv = new ModelAndView();
        //보여줄 view페이지 이름(ooo.mustache)
        mv.setViewName("/testlist");

        //dto객체 형태로 "selectListCreator"이라는 이름으로 세션형성
        mv.addObject("selectListTimeTable", dto);
        return mv;
    }

    @GetMapping("/selecttimetable")
    public ModelAndView selectTimeTable(){
        //실행할 메소드(서비스 부분에 있는 메소드)
        TimeTableDto dto = scriptPaperService.selectTimeTable();

        //세션 객체생셩
        ModelAndView mv = new ModelAndView();
        //보여줄 view페이지 이름(ooo.mustache)
        mv.setViewName("/test");

        //dto객체 형태로 "selectListCreator"이라는 이름으로 세션형성
        mv.addObject("selectTimeTable", dto);
        return mv;
    }

    @GetMapping("/inserttimetable")
    public ModelAndView insertTimeTable(){
        //실행할 메소드(서비스 부분에 있는 메소드)
        scriptPaperService.insertTimeTable();

        //세션 객체생셩
        ModelAndView mv = new ModelAndView();
        //보여줄 view페이지 이름(ooo.mustache)
        mv.setViewName("/test");
        return mv;
    }
    @GetMapping("/updatetimetable")
    public ModelAndView updateTimeTable(){
        //실행할 메소드(서비스 부분에 있는 메소드)
        scriptPaperService.updateTimeTable();

        //세션 객체생셩
        ModelAndView mv = new ModelAndView();
        //보여줄 view페이지 이름(ooo.mustache)
        mv.setViewName("/test");
        return mv;
    }
    @GetMapping("/deletetimetable")
    public ModelAndView deleteTimeTable(){
        //실행할 메소드(서비스 부분에 있는 메소드)
        scriptPaperService.deleteTimeTable();

        //세션 객체생셩
        ModelAndView mv = new ModelAndView();
        //보여줄 view페이지 이름(ooo.mustache)
        mv.setViewName("/test");
        return mv;
    }
}
