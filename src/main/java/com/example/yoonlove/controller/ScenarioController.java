package com.example.yoonlove.controller;

import com.example.yoonlove.dto.ScenarioDto;
import com.example.yoonlove.service.ScenarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ScenarioController {
    @Autowired
    private ScenarioService scenarioService;

    @GetMapping("/scenario/scenario")
    public ModelAndView selectListScenario(){
        List<ScenarioDto> dto = scenarioService.selectListScenario();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/scenario/scenario");
        mv.addObject("selectListScenario", dto);
        return mv;
    }

    @GetMapping("/scenario/{scenario_id}/selectscenario/")
    public ModelAndView selectScenario(ScenarioDto scenarioDto){
        ScenarioDto dto = scenarioService.selectScenario(scenarioDto);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/scenario/selectscenario");
        mv.addObject("selectScenario", dto);
        return mv;
    }

    @GetMapping("/scenario/insertscenarioview")
    public String insertView(){
        return "/scenario/insertscenario";
    }

    @GetMapping("/scenario/insertscenario")
    public String insertScenario(ScenarioDto dto){
        scenarioService.insertScenario(dto);
        return "/scenario/scenario";
    }

    @GetMapping("/scenario/{scenario_id}/updatescenarioview")
    public ModelAndView updateView(ScenarioDto scenarioDto){
        ScenarioDto dto = scenarioService.selectScenario(scenarioDto);
        ModelAndView mv = new ModelAndView();
        mv.addObject("updateScenario", dto);
        mv.setViewName("/scenario/updatescenario");
        return mv;
    }

    @GetMapping("/scenario/{scenario_id}/updatescenario")
    public String updateScenario(ScenarioDto dto){
        scenarioService.updateScenario(dto);
        return "/scenario/scenario";
    }


    @GetMapping("/deletescenario")
    public ModelAndView deleteScenario(ScenarioDto dto){
        //실행할 메소드(서비스 부분에 있는 메소드)
        scenarioService.deleteScenario(dto);

        //세션 객체생셩
        ModelAndView mv = new ModelAndView();
        //보여줄 view페이지 이름(ooo.mustache)
        mv.setViewName("/test");
        return mv;
    }
}
