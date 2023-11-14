package com.example.yoonlove.controller;

import com.example.yoonlove.dto.UserDto;
import com.example.yoonlove.service.DropDownService;
import com.example.yoonlove.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class DropdownController {

    @Autowired
    private DropDownService dropDownService;
    @Autowired
    private UserService userService;

    //프로젝트 옵션 선택에따라 시나리오 옵션 재생성
    @PostMapping("/dropdown/project_id")
    @ResponseBody
    public String dropdwonProject(@RequestParam ("pkid") String pkid, Principal user) throws JsonProcessingException {

        //유저정보 가저오는 dto
        UserDto userInfo = userService.getUser(user.getName());
        String companyId = userInfo.getCompany_id(); //회사 id 스트링

        String jsonList = dropDownService.scenarioOption(pkid, companyId);

        return jsonList;
    }

    //시나리오 옵션 선택에따라 씬 옵션 재생성
    @PostMapping("/dropdown/scenario_id")
    @ResponseBody
    public String dropdwonScenario(@RequestParam ("pkid") String pkid, Principal user) throws JsonProcessingException {
        //유저정보 가저오는 dto
        UserDto userInfo = userService.getUser(user.getName());
        String companyId = userInfo.getCompany_id(); //회사 id 스트링

        String jsonList = dropDownService.sceneOption(pkid, companyId);
        return jsonList;
    }

    @PostMapping("/dropdown/day_id")
    @ResponseBody
    public String dropdwonDay(@RequestParam ("pkid") String pkid) throws JsonProcessingException {
        String jsonList = dropDownService.dayOption(pkid);
        return jsonList;
    }


    //기획명 옵션 선택에 따라 제작인원 편성
    @PostMapping("/dropdown/produce")
    @ResponseBody
    public String dropdwonProduce(@RequestParam ("pkid") String pkid) throws JsonProcessingException {

        String jsonList = dropDownService.produceOption(pkid);
        return jsonList;
    }
}
