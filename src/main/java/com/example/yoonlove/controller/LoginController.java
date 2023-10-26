package com.example.yoonlove.controller;


import com.example.yoonlove.dto.CompanyDto;
import com.example.yoonlove.dto.UserDto;
import com.example.yoonlove.mapper.UserMapper;
import com.example.yoonlove.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @Autowired
    public UserService userService;
    @Autowired
    public BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("login")
    public String login() {
        return "/login/login";
    }

   /* @PostMapping("login/porc")
    public String loginporoc(UserDto dto){
        String rawPw = dto.getPw();
        String encPw = bCryptPasswordEncoder.encode(rawPw);
        userService.loadUserByUsername(dto.getUser_id());
       return null;
    }*/


    @GetMapping("companysignupview")
    public String companysignupview() {
        return "/login/companysignup";
    }

    @GetMapping("companysignup")
    public String companysign(CompanyDto dto) {
        System.out.println("?11?" + dto.toString());
        userService.companysignup(dto);
        return "redirect:/index";
    }


    @PostMapping("ConfirmId")
    public ResponseEntity<?> confirmId(@RequestParam("user_id") String user_id) {
        System.out.println("Controller running with id: " + user_id);

        ModelAndView mv = new ModelAndView();
        UserDto dto = new UserDto();
        dto.setUser_id(user_id);
        mv.setViewName("/login/signup");
        mv.addObject("ConfirmId", userService.selectId(dto));

        boolean isIdAvailable = userService.selectId(dto);
        System.out.println("중복확인 값"+isIdAvailable);
        return ResponseEntity.ok(isIdAvailable);
    }


    @GetMapping("signupview")
    public ModelAndView signview(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/login/signup");
        String test = "ture";
        mv.addObject("ConfirmId" , test);

        return mv;
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