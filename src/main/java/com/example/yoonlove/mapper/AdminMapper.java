package com.example.yoonlove.mapper;

import com.example.yoonlove.dto.DepartmentDto;
import com.example.yoonlove.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {
    //고객정보
    public List<UserDto> selectListUser();
    public UserDto selectUser();
    public void insertUser();
    public void updateUser();
    public void deleteUser();

    //부서정보
    public List<DepartmentDto> selectListDepartment();
    public DepartmentDto selectDepartment();
    public void insertDepartment();
    public void updateDepartment();
    public void deleteDepartment();
}
