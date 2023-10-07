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
        return "redirect:/scenario/scenario";
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
        return "redirect:/scenario/scenario";
    }


    @GetMapping("/scenario/{scenario_id}/deletescenario")
    public String deleteScenario(ScenarioDto dto){
        scenarioService.deleteScenario(dto);
        return "redirect:/scenario/scenario";
    }
}
