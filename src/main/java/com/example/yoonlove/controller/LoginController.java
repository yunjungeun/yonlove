package com.example.yoonlove.controller;


import com.example.yoonlove.dto.CompanyDto;
import com.example.yoonlove.dto.UserDto;
import com.example.yoonlove.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.security.Principal;

@Controller
public class LoginController {

    @Autowired
    public UserService userService;
    @Autowired
    public BCryptPasswordEncoder bCryptPasswordEncoder;


    /*@RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginSuccess(Model model, HttpSession session) {
        session.setAttribute("userLoggedIn", true);
        return "redirect:/index";
    }*/

    @RequestMapping("/login")
    public String login(@RequestParam(name = "error", required = false) String error, Model model){

        if (error != null) {
            model.addAttribute("errorMessage", "아이디와 비밀번호를 확인하세요.");
        }
          return "/main/login";
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
    @ResponseBody
    public String companysign(CompanyDto dto) {
        userService.companysignup(dto);
        System.out.println("dddd"+dto.toString());
        return "/index";
    }


    @PostMapping("ConfirmId")
    public ResponseEntity<?> confirmId(@RequestParam("user_id") String user_id) {
        ModelAndView mv = new ModelAndView();
        UserDto dto = new UserDto();
        dto.setUser_id(user_id);
        mv.setViewName("/login/signup");
        mv.addObject("ConfirmId", userService.selectId(dto));

        boolean isIdAvailable = userService.selectId(dto);
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
    @ResponseBody
    public String sign(UserDto dto){
        userService.signUp(dto);
        return "/cs/qna";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        new SecurityContextLogoutHandler().logout(request,response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/login";
    }

}