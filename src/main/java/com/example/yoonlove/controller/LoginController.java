package com.example.yoonlove.controller;


import com.example.yoonlove.dto.UserDto;
import com.example.yoonlove.mapper.UserMapper;
import com.example.yoonlove.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    public UserService userService;
    @Autowired
    public BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("login")
    public String login(){
        return "/login/login";
    }

    @PostMapping("login/porc")
    public String loginporoc(UserDto dto){
        String rawPw = dto.getPw();
        String encPw = bCryptPasswordEncoder.encode(rawPw);
        userService.loadUserByUsername(dto.getUser_id());
       return null;
    }

}