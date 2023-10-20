package com.example.yoonlove.controller;

import com.example.yoonlove.dto.ActorDto;
import com.example.yoonlove.dto.SceneDto;
import com.example.yoonlove.dto.FileDto;
import com.example.yoonlove.mapper.FileMapper;
import com.example.yoonlove.service.FileService;
import com.example.yoonlove.service.SceneService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
public class SceneController {
    @Autowired
    private SceneService sceneService;
    @Autowired
    private FileService fileService;
    @Autowired
    private FileMapper  fileMapper;


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


        FileDto fileDto = fileService.selectFile(sceneDto);
        SceneDto dto = sceneService.selectScene(sceneDto);
        System.out.println("fk=" + fileDto.getScene_id());

        System.out.println("오류444");
        System.out.println("오류555");

        ModelAndView mv = new ModelAndView();
        mv.addObject("file",fileDto);
        mv.setViewName("scene/sceneselect");
        mv.addObject("selectScene", dto); //여기까지 했어요!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        System.out.println(dto.toString());
        return mv;
    }

    @GetMapping("/scene/insertsceneview")
    public String insertSceneView(){
        return "/scene/sceneinsert";
    }

    @PostMapping("/scene/insertscene")
    public String insertScene(SceneDto dto, FileDto fileDto,MultipartFile file) throws IOException {
            sceneService.insertScene(dto);
        System.out.println("insert 업로드");
        System.out.println(file !=null);

            int lastnum = sceneService.lastPost(dto);
        try {
            System.out.println("오류1111");
            fileService.insertFile(file, lastnum); // FileService를 사용하여 파일 업로드
            System.out.println("오류2222");

            log.info("실행됐을까요?");
        } catch (IOException e) {
           log.info(e.getMessage());
            // 예외 처리
        }


        log.info("실행됐을까요?");
        return "redirect:/scene/scene";
    }

    @GetMapping("/scene/{scene_id}/updatesceneview")
    public ModelAndView updateSceneView(SceneDto sceneDto){

        System.out.println("오류11");
        SceneDto dto = sceneService.selectScene(sceneDto);
        FileDto fileDto = fileService.selectFile(sceneDto);
        System.out.println("오류22");
        System.out.println("오류33");
        ModelAndView mv = new ModelAndView();
        System.out.println("오류44");
        mv.setViewName("/scene/sceneupdate");
        mv.addObject("updateScene",dto);
        mv.addObject("file", fileDto);
        return mv;
    }

    @PostMapping("/scene/{scene_id}/updatescene")
    public String updateScene(SceneDto dto, MultipartFile newfile) {
        System.out.println("insert 업로드");
        System.out.println(newfile !=null);


        System.out.println("오류111111111111111111111111111111111");

        try {
            fileService.updateFile(dto, newfile); // 파일수정해서 업로드하는 메소드!!!!!!!
        }catch (IOException e) {
            e.printStackTrace();

        }
        System.out.println("이게 찍혔다면 파일 업로드 수정 처리 완료!!!!!!!");

        sceneService.updateScene(dto);

        return "redirect:/scene/scene";
    }
    @GetMapping("/scene/{scene_id}/deletescene")
    public String deleteScene(SceneDto dto){

        System.out.println("1111111111");

        sceneService.deleteScene(dto);

        System.out.println("22322222222");

        return "redirect:/scene/scene";
    }


    //출연자 정보
    @GetMapping("scene/actor")
    public ModelAndView selectListActor(){
        List<ActorDto> dto = sceneService.selectListActor();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("scene/actor");
        mv.addObject("selectListActor", dto);
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
