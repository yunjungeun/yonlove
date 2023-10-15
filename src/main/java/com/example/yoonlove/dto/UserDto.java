package com.example.yoonlove.dto;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

@Data
public class UserDto implements UserDetails {

        private String user_id;
        private String pw;
        private String pw2;
        private String user_name;
        private String nickname;
        private int phone;
        private int tell;
        private String email;
        private String address1;
        private String address2;
        private int zipcode;
        private String company_user;
        private String grade;
        private String authority;
        private String company_id;
        private String dpt_id;
        private Date sign_date;
        private int basic_pay;
        private int total_pay;


    @Override//권한 반환
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("user"));//"user"권한만 반환함
    }

    @Override//비번반환
    public String getPassword() {
        return this.pw;
    }

    @Override//id값 반환
    public String getUsername() {
        return this.user_id;
    }

    @Override//계정만료여부 반환
    public boolean isAccountNonExpired() {
        //만료됫는지 확인하는 로직들어가야함
        return true; //만료되지 않았음
    }

    @Override//계정잠금여부 반환
    public boolean isAccountNonLocked() {
        //자금됫는지 확인하는 로직들어가야함
        return true;//잠금되지 않앗음
    }

    @Override//패스워드 만료여부 반환
    public boolean isCredentialsNonExpired() {
        //비번 만료되엇는지 확인 로직
        return true;//만료되지 않앗음
    }

    @Override//계정 사용 가능 여부 반환
    public boolean isEnabled() {
        //계정사용가능하는지 확인로직
        return true;//사용가능
    }
}

