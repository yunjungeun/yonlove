package com.example.yoonlove.controller;

import com.example.yoonlove.dto.*;
import com.example.yoonlove.service.PagingService;
import com.example.yoonlove.service.SceneService;
import com.example.yoonlove.service.ScriptPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SceneController {
    @Autowired
    private SceneService sceneService;
    @Autowired
    private PagingService pagingService;
    @Autowired
    private ScriptPaperService scriptPaperService;


    @GetMapping("scene/scene")
    public ModelAndView selectListScene(PageDto pdto, @RequestParam(name="page", defaultValue = "1") int page){
        PageDto pageDto = new PageDto("scene","scene_id",page,pdto);
        PageDto pageInfo = pagingService.paging(pageDto);
        List<PageDto> pageList = pagingService.pageList(pageInfo.getPageStart(),pageInfo.getPageEnd(),page);
        String rink = pagingService.pageRink(pageDto);


        List<SceneDto> dto = sceneService.selectListScene(pageInfo);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/scene/scene");
        mv.addObject("selectListScene", dto);
        mv.addObject("prefixUrl", "scene");
        mv.addObject("paging", pageInfo);  //페이징정보
        mv.addObject("pagelist", pageList); //페이지 하단부 페이지 리스트
        mv.addObject("pageRink",rink); //검색유무에 다라 동적 페이지링크를 뷰페이지에 전달
        return mv;
    }

    @GetMapping("scene/{scene_id}/selectscene")
    public ModelAndView selectScene(SceneDto sceneDto, @RequestParam(name="page", defaultValue = "1") int page, PageDto pdto ){
        SceneDto dto = sceneService.selectScene(sceneDto);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("scene/sceneselect");
        mv.addObject("selectScene", dto);

        //서브게시판 리스트
        pdto.setPkid(dto.getScene_id());

        PageDto pageDto = new PageDto("scriptpaper","script_id", page,pdto);
        PageDto pageInfo = pagingService.paging(pageDto);
        List<PageDto> pageList = pagingService.pageList(pageInfo.getPageStart(),pageInfo.getPageEnd(),page);
        String rink = pagingService.subPageRink(pageDto,"scene");

        List<ScriptPaperDto> subList = scriptPaperService.selectListScriptPaper(pageInfo);
        mv.addObject("selectListScriptPaper", subList);

        //서브 페이징에 필요한섹션
        mv.addObject("pageDto", pageDto);
        mv.addObject("prefixUrl", "scene"); //컨트롤러 이름
        mv.addObject("paging", pageInfo);  //페이징정보
        mv.addObject("pagelist", pageList); //페이지 하단부 페이지 리스트
        mv.addObject("pageRink",rink); //검색유무에 다라 동적 페이지링크를 뷰페이지에 전달

        return mv;
    }

    @GetMapping("/scene/insertsceneview")
    public ModelAndView insertSceneView(){
        List<ScenarioDto> scenarioDto = sceneService.selectFk();

        ModelAndView mv = new ModelAndView();
        mv.addObject("fkList",scenarioDto);
        mv.setViewName("/scene/sceneinsert");
        return mv;
    }

    @GetMapping("/scene/insertscene")
    public String insertScene(SceneDto dto){
        sceneService.insertScene(dto);
        return "redirect:/scene/scene";
    }

    @GetMapping("/scene/{scene_id}/updatesceneview")
    public ModelAndView updateSceneView(SceneDto sceneDto){
        SceneDto dto = sceneService.selectScene(sceneDto);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/scene/sceneupdate");
        mv.addObject("updateScene",dto);
        return mv;
    }

    @GetMapping("/scene/{scene_id}/updatescene")
    public String updateScene(SceneDto dto){
        sceneService.updateScene(dto);
        return "redirect:/scene/scene";
    }
    @GetMapping("/scene/{scene_id}/deletescene")
    public String deleteScen(SceneDto dto){
        sceneService.deleteScene(dto);
        return "redirect:/scene/scene";
    }


    //출연자 정보
    @GetMapping("scene/actor")
    public ModelAndView selectListActor(PageDto pdto, @RequestParam(name="page", defaultValue = "1") int page){
        PageDto pageDto = new PageDto("actor","actor_id",page,pdto);
        PageDto pageInfo = pagingService.paging(pageDto);
        List<PageDto> pageList = pagingService.pageList(pageInfo.getPageStart(),pageInfo.getPageEnd(),page);
        String rink = pagingService.pageRink(pageDto);

        List<ActorDto> dto = sceneService.selectListActor(pageInfo);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("scene/actor");
        mv.addObject("selectListActor", dto);

        mv.addObject("prefixUrl", "scene");
        mv.addObject("paging", pageInfo);  //페이징정보
        mv.addObject("pagelist", pageList); //페이지 하단부 페이지 리스트
        mv.addObject("pageRink",rink); //검색유무에 다라 동적 페이지링크를 뷰페이지에 전달

        return mv;
    }

    @GetMapping("scene/{actor_id}/selectactor")
    public ModelAndView selectActor(ActorDto actorDto){
        ActorDto dto = sceneService.selectActor(actorDto);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("scene/actorselect");
        mv.addObject("selectActor", dto);
        return mv;
    }

    @GetMapping("scene/insertactorview")
    public String insertActorView(){
        return "scene/actorinsert";
    }
    @GetMapping("scene/insertactor")
    public String insertActor(ActorDto dto){
        sceneService.insertActor(dto);
        return "redirect:/scene/actor";
    }


    @GetMapping("scene/{actor_id}/updateactorview")
    public ModelAndView updateActorView(ActorDto actorDto){
        ActorDto dto = sceneService.selectActor(actorDto);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("scene/actorupdate");
        mv.addObject("updateActor", dto);
        return mv;
    }

    @GetMapping("scene/{actor_id}/updateactor")
    public String updateActor(ActorDto dto){
        sceneService.updateActor(dto);
        return "redirect:/scene/actor";
    }
    @GetMapping("scene/{actor_id}/deleteactor")
    public String deleteActor(ActorDto dto){
        sceneService.deleteActor(dto);
        return "redirect:/scene/actor";
    }

}
