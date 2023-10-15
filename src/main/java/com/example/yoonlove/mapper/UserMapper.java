package com.example.yoonlove.mapper;

import com.example.yoonlove.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    public UserDto getUser(String user_id);
    public void signUp(UserDto dto);
}
