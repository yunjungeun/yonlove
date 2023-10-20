package com.example.yoonlove.controller;

import com.example.yoonlove.dto.ActorDto;
import com.example.yoonlove.dto.ScenarioDto;
import com.example.yoonlove.dto.SceneDto;
import com.example.yoonlove.service.SceneService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
    public ModelAndView selectListScene(PageDto pdto, @RequestParam(name="page", defaultValue = "1") int page){
        PageDto pageDto = new PageDto("scene","scene_id",page,pdto);
        PageDto pageInfo = pagingService.paging(pageDto);
        List<PageDto> pageList = pagingService.pageList(pageInfo.getPageStart(),pageInfo.getPageEnd(),page);
        String rink = pagingService.pageRink(pageDto);


        List<SceneDto> dto = sceneService.selectListScene(pageInfo);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/scene/scene");
        mv.addObject("selectListScene", dto);

        mv.addObject("paging", pageInfo);  //페이징정보
        mv.addObject("pagelist", pageList); //페이지 하단부 페이지 리스트
        mv.addObject("pageRink",rink); //검색유무에 다라 동적 페이지링크를 뷰페이지에 전달
        return mv;
    }

    @GetMapping("scene/{scene_id}/selectscene")
    public ModelAndView selectScene(SceneDto sceneDto){
        FileDto fileDto = fileService.selectFile(sceneDto);
        SceneDto dto = sceneService.selectScene(sceneDto);

        ModelAndView mv = new ModelAndView();
        mv.addObject("file",fileDto);
        mv.setViewName("scene/sceneselect");
        mv.addObject("selectScene", dto); //여기까지 했어요!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        return mv;
    }

    @GetMapping("/scene/insertsceneview")
    public String insertSceneView(){
        return "/scene/sceneinsert";
    }

    @PostMapping("/scene/insertscene")
    public String insertScene(SceneDto dto,MultipartFile file) throws IOException {
            sceneService.insertScene(dto);

            int lastnum = sceneService.lastPost(dto);

            if(file.isEmpty()){
                fileService.insertNull(lastnum);
            }else {
                try {

                    fileService.insertFile(file, lastnum); // FileService를 사용하여 파일 업로드
                } catch (IOException e) {
                    log.info(e.getMessage());
                    // 예외 처리
                }
            }

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

        try {
            fileService.updateFile(dto, newfile); // 파일수정해서 업로드하는 메소드!!!!!!!
        }catch (IOException e) {
            e.printStackTrace();

        }
        sceneService.updateScene(dto);
        return "redirect:/scene/scene";
    }
    @GetMapping("/scene/{scene_id}/deletescene")
    public String deleteScene(SceneDto dto){
        sceneService.deleteScene(dto);
        return "redirect:/scene/scene";
    }


    @GetMapping("/scene/{scene_id}/deletefile")
    public String deletefile(SceneDto dto){
        String id= dto.getScene_id();
        fileService.deleFile(dto);
        fileService.deletdb(dto);
        return "redirect:/scene/"+id+"/updatesceneview";
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
