package com.example.yoonlove.controller;

import com.example.yoonlove.dto.ActorDto;
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

    @GetMapping("/scene/scene")
    public ModelAndView selectListScene(){
        //실행할 메소드(서비스 부분에 있는 메소드)
        List<SceneDto> dto = sceneService.selectListScene();

        //세션 객체생셩
        ModelAndView mv = new ModelAndView();
        //보여줄 view페이지 이름(ooo.mustache)
        mv.setViewName("/scene/scene");

        //dto객체 형태로 "selectListCreator"이라는 이름으로 세션형성
        mv.addObject("selectListScene", dto);
        return mv;
    }

    @GetMapping("/selectscene")
    public ModelAndView selectScene(SceneDto sceneDto){
        //실행할 메소드(서비스 부분에 있는 메소드)
        SceneDto dto = sceneService.selectScene(sceneDto);

        //세션 객체생셩
        ModelAndView mv = new ModelAndView();
        //보여줄 view페이지 이름(ooo.mustache)
        mv.setViewName("/test");

        //dto객체 형태로 "selectListCreator"이라는 이름으로 세션형성
        mv.addObject("selectScene", dto);
        return mv;
    }

    @GetMapping("/scene/insertsceneview")
    public String insertSceneView(){
        return "/scene/sceneinsert";
    }

    @GetMapping("/scene/insertscene")
    public String insertScene(SceneDto dto){
        System.out.println("test1");
        sceneService.insertScene(dto);
        System.out.println("test2");
        return "redirect:/scene/scene";
    }
    @GetMapping("/updatescene")
    public ModelAndView updateScene(SceneDto dto){
        //실행할 메소드(서비스 부분에 있는 메소드)
        sceneService.updateScene(dto);

        //세션 객체생셩
        ModelAndView mv = new ModelAndView();
        //보여줄 view페이지 이름(ooo.mustache)
        mv.setViewName("/test");
        return mv;
    }
    @GetMapping("/deletescene")
    public ModelAndView deleteScen(SceneDto dto){
        //실행할 메소드(서비스 부분에 있는 메소드)
        sceneService.deleteScene(dto);

        //세션 객체생셩
        ModelAndView mv = new ModelAndView();
        //보여줄 view페이지 이름(ooo.mustache)
        mv.setViewName("/test");
        return mv;
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
