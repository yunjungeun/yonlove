package com.example.yoonlove.controller;

import com.example.yoonlove.dto.*;
import com.example.yoonlove.service.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Slf4j
@Controller
public class SceneController {
    @Autowired
    private SceneService sceneService;
    @Autowired
    private FileService fileService;
    @Autowired
    private PagingService pagingService;
    @Autowired
    private ScriptPaperService scriptPaperService;
    @Autowired
    private DropDownService dropDownService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProjectService projectService;

    @GetMapping("scene/scene")
    public ModelAndView selectListScene(PageDto pdto, @RequestParam(name="page", defaultValue = "1") int page,
                                        Principal user){
        //유저정보 가저오는 dto
        UserDto userInfo = userService.getUser(user.getName());
        String companyId = userInfo.getCompany_id(); //회사 id 스트링

        PageDto pageDto = new PageDto("scene","scene_id",page,pdto, companyId);
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
    public ModelAndView selectScene(SceneDto sceneDto, @RequestParam(name="page", defaultValue = "1") int page, PageDto pdto,
                                    Principal user){
        //유저정보 가저오는 dto
        UserDto userInfo = userService.getUser(user.getName());
        String companyId = userInfo.getCompany_id(); //회사 id 스트링

        ModelAndView mv = new ModelAndView();
        SceneDto dto = sceneService.selectScene(sceneDto);

        FileDto fileDto = fileService.selectSceneFile(sceneDto);

        //파일 없이 업로드해서 파일테이블이 생성이 안되 오류발생하는 부분을 처리//근본없는 해결방법인거 같음
        if(fileDto != null){
            mv.addObject("file",fileDto);
        }else{
            FileDto nullFileDto = new FileDto();
            nullFileDto.setFile_path(" ");
            mv.addObject("file",nullFileDto);
        }

        mv.setViewName("scene/sceneselect");
        mv.addObject("selectScene", dto);

        //서브게시판 리스트
        pdto.setPkid(dto.getScene_id());

        PageDto pageDto = new PageDto("scriptpaper","script_id", page,pdto,companyId);
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
    public ModelAndView insertSceneView(Principal user) throws JsonProcessingException{
        //유저정보 가저오는 dto
        UserDto userInfo = userService.getUser(user.getName());
        String companyId = userInfo.getCompany_id(); //회사 id 스트링
        String jsonListProject = dropDownService.dropDownOption("project",null, companyId);
        ModelAndView mv = new ModelAndView();
        mv.addObject("fkList", jsonListProject);
        mv.setViewName("/scene/sceneinsert");
        return mv;
    }

    @GetMapping("/scene/insertscene")
    @ResponseBody
    public String insertScene(SceneDto dto) {
            sceneService.insertScene(dto);
        return "/scene/scene";
    }

    @GetMapping("/scene/{scene_id}/updatesceneview")
    public ModelAndView updateSceneView(SceneDto sceneDto){
        SceneDto dto = sceneService.selectScene(sceneDto);
        FileDto fileDto = fileService.selectSceneFile(sceneDto);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/scene/sceneupdate");
        mv.addObject("updateScene",dto);
        mv.addObject("file", fileDto);
        return mv;
    }

    @GetMapping("/scene/{scene_id}/updatescene")
    @ResponseBody
    public String updateScene(SceneDto dto) {
        sceneService.updateScene(dto);
        return "/scene/scene";
    }
    @GetMapping("/scene/{scene_id}/deletescene")
    public String deleteScene(SceneDto dto){
        sceneService.deleteScene(dto);
        return "redirect:/scene/scene";
    }


    //출연자 정보
    @GetMapping("scene/actor")
    public ModelAndView selectListActor(PageDto pdto, @RequestParam(name="page", defaultValue = "1") int page,
                                        Principal user){
        //유저정보 가저오는 dto
        UserDto userInfo = userService.getUser(user.getName());
        String companyId = userInfo.getCompany_id(); //회사 id 스트링

        PageDto pageDto = new PageDto("actor","act_id",page,pdto, companyId);
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

    @GetMapping("scene/{act_id}/selectactor")
    public ModelAndView selectActor(ActorDto actorDto){
        ActorDto dto = sceneService.selectActor(actorDto);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("scene/actorselect");
        mv.addObject("selectActor", dto);
        return mv;
    }

    @GetMapping("scene/insertactorview")
    public ModelAndView insertActorView(Principal user) throws JsonProcessingException {
        //유저정보 가저오는 dto
        UserDto userInfo = userService.getUser(user.getName());
        String companyId = userInfo.getCompany_id(); //회사 id 스트링

        String jsonListProject = dropDownService.dropDownOption("project",null, companyId);

        ModelAndView mv = new ModelAndView();
        mv.addObject("fkList", jsonListProject);
        mv.setViewName("/scene/actorinsert");
        return mv;
    }
    @GetMapping("scene/insertactor")
    @ResponseBody
    public String insertActor(ActorDto dto){
        sceneService.insertActor(dto);
        return "/scene/actor";
    }


    @GetMapping("scene/{act_id}/updateactorview")
    public ModelAndView updateActorView(ActorDto actorDto){
        ActorDto dto = sceneService.selectActor(actorDto);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("scene/actorupdate");
        mv.addObject("updateActor", dto);
        return mv;
    }

    @GetMapping("scene/{act_id}/updateactor")
    @ResponseBody
    public String updateActor(ActorDto dto){
        sceneService.updateActor(dto);

        ProduceDto produceDto = new ProduceDto();
        produceDto = sceneService.transToProduceDto(dto);
        projectService.updateProduce(produceDto);
        return "/scene/actor";
    }
    @GetMapping("scene/{act_id}/deleteactor")
    public String deleteActor(ActorDto dto){
        sceneService.deleteActor(dto);
        return "redirect:/scene/actor";
    }
}