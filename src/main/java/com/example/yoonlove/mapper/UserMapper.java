package com.example.yoonlove.mapper;

import com.example.yoonlove.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {



    public UserDto selectUser(UserDto dto);

   /* public UserDto selectUserBySessionId(UserDto dto);*/

    public int insertUser(UserDto dto);

    public int updatetUser(UserDto dto);

    public int updateUserSessionInit(UserDto dto);





}
