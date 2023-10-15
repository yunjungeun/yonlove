package com.example.yoonlove.controller;


import com.example.yoonlove.dto.UserDto;
import com.example.yoonlove.mapper.UserMapper;
import com.example.yoonlove.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
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

   /* @PostMapping("login/porc")
    public String loginporoc(UserDto dto){
        String rawPw = dto.getPw();
        String encPw = bCryptPasswordEncoder.encode(rawPw);
        userService.loadUserByUsername(dto.getUser_id());
       return null;
    }*/

    @GetMapping("signupview")
    public String signview(){
        return "/login/signup";
    }
    @GetMapping("signup")
    public String sign(UserDto dto){
        System.out.println("sql실행전"+dto.toString());
        userService.signUp(dto);
        System.out.println("sql실행후");
        return "/cs/qna";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        new SecurityContextLogoutHandler().logout(request,response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/login";
    }

}