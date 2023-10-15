package com.example.yoonlove.service;

import com.example.yoonlove.dto.UserDto;
import com.example.yoonlove.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {


    @Autowired
    private UserMapper userMapper;
    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserDto getUser(String user_id){return userMapper.getUser(user_id);}

    @Override//스프링시큐리티 에서 사용자 정보를 가저오는 메소드
    public UserDetails loadUserByUsername(String user_id) throws UsernameNotFoundException {
        UserDto dto = new UserDto();
        dto = userMapper.getUser(user_id);
        if(dto != null){
            List<GrantedAuthority> authorities = new ArrayList<>();

            return new User(dto.getUser_id(), dto.getPw(), authorities);
        }
        return null;
    }
}
