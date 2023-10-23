package com.example.yoonlove.controller;

import com.example.yoonlove.dto.PageDto;
import com.example.yoonlove.dto.ScenarioDto;
import com.example.yoonlove.dto.SceneDto;
import com.example.yoonlove.service.PagingService;
import com.example.yoonlove.service.ScenarioService;
import com.example.yoonlove.service.SceneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ScenarioController {
    @Autowired
    private ScenarioService scenarioService;
    @Autowired
    private PagingService pagingService;
    @Autowired
    private SceneService sceneService;


    @GetMapping("/scenario/scenario")
    public ModelAndView selectListScenario(PageDto pdto, @RequestParam(name="page", defaultValue = "1") int page){
        PageDto pageDto = new PageDto("scenario","scenario_id",page,pdto);
        PageDto pageInfo = pagingService.paging(pageDto);
        List<PageDto> pageList = pagingService.pageList(pageInfo.getPageStart(),pageInfo.getPageEnd(),page);
        String rink = pagingService.pageRink(pageDto);

        List<ScenarioDto> dto = scenarioService.selectListScenario(pageInfo);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/scenario/scenario");
        mv.addObject("selectListScenario", dto);

        mv.addObject("prefixUrl", "scenario");
        mv.addObject("paging", pageInfo);  //페이징정보
        mv.addObject("pagelist", pageList); //페이지 하단부 페이지 리스트
        mv.addObject("pageRink",rink); //검색유무에 다라 동적 페이지링크를 뷰페이지에 전달
        return mv;
    }

    @GetMapping("/scenario/{scenario_id}/selectscenario")
    public ModelAndView selectScenario(ScenarioDto scenarioDto, @RequestParam(name="page", defaultValue = "1") int page, PageDto pdto ){
        //기존 select
        ScenarioDto dto = scenarioService.selectScenario(scenarioDto);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/scenario/selectscenario");
        mv.addObject("selectScenario", dto);
        //기존 select end

        //fk 검색 값 설정
        pdto.setPkid(dto.getScenario_id());
        PageDto pageDto = new PageDto("scene","scene_id",page,pdto);

        PageDto pageInfo = pagingService.paging(pageDto);
        List<PageDto> pageList = pagingService.pageList(pageInfo.getPageStart(),pageInfo.getPageEnd(),page);
        String rink = pagingService.subPageRink(pageDto,"scenario");
        List<SceneDto> subList = sceneService.selectListScene(pageInfo);
        mv.addObject("selectListScene", subList);

        mv.addObject("pageDto", pageDto);
        mv.addObject("prefixUrl", "scenario"); //컨트롤러 이름
        mv.addObject("paging", pageInfo);  //페이징정보
        mv.addObject("pagelist", pageList); //페이지 하단부 페이지 리스트
        mv.addObject("pageRink",rink); //검색유무에 다라 동적 페이지링크를 뷰페이지에 전달


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
