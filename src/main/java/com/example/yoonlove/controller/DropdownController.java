package com.example.yoonlove.controller;

import com.example.yoonlove.service.DropDownService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DropdownController {

    @Autowired
    private DropDownService dropDownService;

    //프로젝트 옵션 선택에따라 시나리오 옵션 재생성
    @PostMapping("/fk3")
    @ResponseBody
    public String fk3test(@RequestParam ("pkid") String pkid) throws JsonProcessingException {
        String jsonList = dropDownService.scenarioOption(pkid);
        return jsonList;
    }

    //시나리오 옵션 선택에따라 씬 옵션 재생성
    @PostMapping("/fk2")
    @ResponseBody
    public String fk2test(@RequestParam ("pkid") String pkid) throws JsonProcessingException {
        String jsonList = dropDownService.sceneOption(pkid);
        return jsonList;
    }
}
