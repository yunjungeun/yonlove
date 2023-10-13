package com.example.yoonlove.service;

import com.example.yoonlove.dto.UserDto;
import com.example.yoonlove.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;


    public UserDto loginUser(UserDto userDto) {
        UserDto user = userMapper.selectUser(userDto);
        return user;

    }

    public void isKeepLogin(UserDto userDto) {
        if (userDto.isAutoLogin()) {
            userMapper.updatetUser(userDto);

        } else {
            userMapper.updateUserSessionInit(userDto);

        }
    }


    public boolean joinUser(UserDto userDto) {
        UserDto existingUser = userMapper.selectUser(userDto.getEmail());
        if (existingUser != null) {
            return false;
        }

        int cnt = userMapper.insertUser(userDto);
        if (cnt > 0) {
            return true;
        }
        return false;
    }




}
