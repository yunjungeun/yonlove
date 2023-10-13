package com.example.yoonlove.controller;

import com.example.yoonlove.dto.UserDto;
import com.example.yoonlove.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;

@Controller
public class LoginController {

    /**
     * 1. 회원가입
     * 1-1. 회원 가입 페이지
     * 1-2. 회원 가입 요청
     * - ID, PW, 이름 기타등등
     * 1-3. 회원가입 성공 페이지
     * 1-4. 회원가입 실패 페이지(팝업....경고창)
     * 2. 로그인
     * 2-1 로그인 페이지
     * 2-2 로그인 요청
     * - ID, PW
     * 2-3 로그인 성공시 페이지이동 (메인, 로그인페이지 이전화면)
     * 2-4 로그인 실패시 페이지 (팝업... 경고창 등등)
     */

    @Autowired
    UserService userService;

    @GetMapping("/login/loginView")
    public ModelAndView loginView() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/login/loginView");
        mv.setStatus(HttpStatus.valueOf(200));
        return mv;
    }



    @GetMapping("/login/login")
    public boolean loginUser(@RequestBody UserDto userDto, HttpServletRequest request, HttpServletResponse response) {
        UserDto user = userService.loginUser(userDto);
        if (user == null) return false;

        HttpSession session = request.getSession();
        session.setAttribute("user", user);


        if (userDto.isAutoLogin()) {
            String sessionId = session.getId();
            int expiry = 60 * 60 * 24 * 7;
            Date expiredAt = new Date(System.currentTimeMillis() + (expiry * 1000));

            UserDto dto = new UserDto();
            dto.setAutoLogin(true);
            dto.setSessionId(sessionId);
            dto.setExpiredAt(expiredAt);
            dto.setEmail(userDto.getEmail());
            userService.isKeepLogin(dto);

            Cookie loginCookie = new Cookie("loginCookie", sessionId);
            loginCookie.setPath("/");
            loginCookie.setMaxAge(expiry); // 7일
            response.addCookie(loginCookie);
        }
        return true;


    }


}






    /*@GetMapping("logout")
    public boolean loginoutUser(UserDto dto, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        UserDto user = (UserDto) session.getAttribute("user");
        session.removeAttribute("user");
        session.invalidate();

        Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
        if(loginCookie != null)
        {
            Map<String, Object> inParam = new HashMap<>();
            inParam.put("isKeepLogin", false);
            inParam.put("email", user.get("email"));
            userService.isKeepLogin(inParam);

            loginCookie.setMaxAge(0);
            response.addCookie(loginCookie);
        }
        return true;
*/









