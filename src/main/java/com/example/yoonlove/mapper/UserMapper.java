package com.example.yoonlove.mapper;

import com.example.yoonlove.dto.CompanyDto;
import com.example.yoonlove.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.security.Principal;

@Mapper
public interface UserMapper {

    public UserDto getUser(String user_id);
    public void signUp(UserDto dto);

    public UserDto getNick(String user_id);
    public void insertCompany(CompanyDto companyDto);

   public String lastCompanyID();

    public boolean selectId(UserDto dto);

    public void updateUserCompanyID(String user, String company);

    public void updateUser(UserDto userDto);
}
