package com.example.yoonlove.controller;

import com.example.yoonlove.dto.ActorDto;
import com.example.yoonlove.dto.ScenarioDto;
import com.example.yoonlove.dto.SceneDto;
import com.example.yoonlove.service.SceneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SceneController {
    @Autowired
    private SceneService sceneService;

    @GetMapping("scene/scene")
    public ModelAndView selectListScene(){
        List<SceneDto> dto = sceneService.selectListScene();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/scene/scene");
        mv.addObject("selectListScene", dto);
        return mv;
    }

    @GetMapping("scene/{scene_id}/selectscene")
    public ModelAndView selectScene(SceneDto sceneDto){
        SceneDto dto = sceneService.selectScene(sceneDto);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("scene/sceneselect");
        mv.addObject("selectScene", dto);
        return mv;
    }

    @GetMapping("/scene/insertsceneview")
    public String insertSceneView(){
        return "/scene/sceneinsert";
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
    @GetMapping("/listactor")
    public ModelAndView selectListActor(){
        //실행할 메소드(서비스 부분에 있는 메소드)
        List<ActorDto> dto = sceneService.selectListActor();

        //세션 객체생셩
        ModelAndView mv = new ModelAndView();
        //보여줄 view페이지 이름(ooo.mustache)
        mv.setViewName("/testlist");

        //dto객체 형태로 "selectListCreator"이라는 이름으로 세션형성
        mv.addObject("selectListActor", dto);
        return mv;
    }

    @GetMapping("/selectactor")
    public ModelAndView selectActor(ActorDto actorDto){
        //실행할 메소드(서비스 부분에 있는 메소드)
        ActorDto dto = sceneService.selectActor(actorDto);

        //세션 객체생셩
        ModelAndView mv = new ModelAndView();
        //보여줄 view페이지 이름(ooo.mustache)
        mv.setViewName("/test");

        //dto객체 형태로 "selectListCreator"이라는 이름으로 세션형성
        mv.addObject("selectActor", dto);
        return mv;
    }

    @GetMapping("/insertactor")
    public ModelAndView insertActor(ActorDto dto){
        //실행할 메소드(서비스 부분에 있는 메소드)
        sceneService.insertActor(dto);

        //세션 객체생셩
        ModelAndView mv = new ModelAndView();
        //보여줄 view페이지 이름(ooo.mustache)
        mv.setViewName("/test");
        return mv;
    }
    @GetMapping("/updateactor")
    public ModelAndView updateActor(ActorDto dto){
        //실행할 메소드(서비스 부분에 있는 메소드)
        sceneService.updateActor(dto);

        //세션 객체생셩
        ModelAndView mv = new ModelAndView();
        //보여줄 view페이지 이름(ooo.mustache)
        mv.setViewName("/test");
        return mv;
    }
    @GetMapping("/deleteactor")
    public ModelAndView deleteActor(ActorDto dto){
        //실행할 메소드(서비스 부분에 있는 메소드)
        sceneService.deleteActor(dto);

        //세션 객체생셩
        ModelAndView mv = new ModelAndView();
        //보여줄 view페이지 이름(ooo.mustache)
        mv.setViewName("/test");
        return mv;
    }

}
