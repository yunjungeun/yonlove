package com.example.yoonlove.service;

import com.example.yoonlove.dto.UserDto;
import com.example.yoonlove.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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

    @Override//아이디로 사용자 비밀번호를 리턴하느 메소드
    public UserDetails loadUserByUsername(String user_id) throws UsernameNotFoundException {
        System.out.println(user_id);
        UserDto dto = new UserDto();
        dto = userMapper.getUser(user_id);
        List<GrantedAuthority> authorities = new ArrayList<>();
        if(dto == null){
            throw new UsernameNotFoundException("그런사람 없음");
        }
        if("user".equals(dto.getAuthority())){
            authorities.add(new SimpleGrantedAuthority(dto.getAuthority()));
        }else{
            authorities.add(new SimpleGrantedAuthority("no"));
        }
        //스프링시큐리티의 User객체를 반환 = 아이디, 비번, 권한을 리턴/ 시큐리티는 리턴값을 가지고 검사
        return new User(dto.getUser_id(),dto.getPw(), authorities);
    }

    public void signUp(UserDto dto){
        UserDto userDto = new UserDto();
        userDto.setUser_id(dto.getUser_id());
        userDto.setAuthority(dto.getAuthority());
        userDto.setPw(bCryptPasswordEncoder.encode(dto.getPw()));
        userMapper.signUp(userDto);
        System.out.println("암호화 완료"+userDto.getPw());
    }
}
