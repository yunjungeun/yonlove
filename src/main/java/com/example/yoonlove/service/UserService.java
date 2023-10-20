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

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public String getnick(Principal user){
        UserDto dto = userMapper.getNick(user.getName());
        String user_nick = dto.getNickname();
        return user_nick;
    }

    //유저 id를 확인하느 메소드
    public UserDto getUser(String user_id){return userMapper.getUser(user_id);}

    @Override//아이디로 사용자 비밀번호를 리턴하느 메소드
    public UserDetails loadUserByUsername(String user_id) throws UsernameNotFoundException {
        UserDto dto = userMapper.getUser(user_id);
        List<GrantedAuthority> authorities = new ArrayList<>();
        if(dto == null){
            throw new UsernameNotFoundException("그런사람 없음");
        }
        //권한확인해서 권한관리 객체에 추가
        if("user".equals(dto.getAuthority())){
            authorities.add(new SimpleGrantedAuthority(dto.getAuthority()));
        }else if("admin".equals(dto.getAuthority())){
            authorities.add(new SimpleGrantedAuthority(dto.getAuthority()));

        }else{
            authorities.add(new SimpleGrantedAuthority("no"));
        }

        //스프링시큐리티의 User객체를 시큐리티에 반환 = 아이디, 비번, 권한을 리턴/ 시큐리티는 리턴값을 가지고 검사
        return new User(dto.getUser_id(),dto.getPw(), authorities);
    }

    //회원가입 정보에서 비밀번호를 암호화하고 db에 등록
    public void signUp(UserDto dto){
        //회원가입폼에서 가저온 정보중 비밀번호를 암호화해서 변경
        dto.setPw(bCryptPasswordEncoder.encode(dto.getPw()));
        //사용자가 작성한 내용 + 암호화된 비번을 db등록
        userMapper.signUp(dto);
    }

}
