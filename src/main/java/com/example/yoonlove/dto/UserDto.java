package com.example.yoonlove.dto;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Data
public class UserDto implements UserDetails {

        private String user_id;
        private String pw;
        private String pw2;
        private String user_name;
        private String nickname;
        private String phone;
        private String tell;
        private String email;
        private String address1;
        private String address2;
        private String zip_code;
        private String company_user;
        private String grade;
        private String authority;
        private String company_id;
        private String dpt_id;
        private String sign_date;
        private String address3;
        private int total_pay;


    @Override//권한 반환                    스프링시큐리티 유저디테일 인터페이스에 정의된 메서드
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //권한 컬렉션 선언
        ArrayList<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
        //권한 컬렉션에 Dto의 권한값인 authority를 추가
        //SimpleGrantedAuthority는 GrantedAuthority인터페이스의 구현체
        auth.add(new SimpleGrantedAuthority(this.authority));
        return auth;
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

