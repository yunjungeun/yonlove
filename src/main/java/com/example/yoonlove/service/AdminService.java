package com.example.yoonlove.service;

import com.example.yoonlove.dto.DepartmentDto;
import com.example.yoonlove.dto.UserDto;
import com.example.yoonlove.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    private AdminMapper adminMapper;

    //유저정보
    public UserDto selectUser(){
        return adminMapper.selectUser();
    }
    public List<UserDto> selectListUser() {
        return adminMapper.selectListUser();
    }
    public void insertUser(){
        adminMapper.insertUser();
    }
    public void updateUser(){
        adminMapper.updateUser();
    }
    public void deleteUser(){
        adminMapper.deleteUser();
    }

    //부서정보
    public DepartmentDto selectDepartment(){
        return adminMapper.selectDepartment();
    }
    public List<DepartmentDto> selectListDepartment() {
        return adminMapper.selectListDepartment();
    }
    public void insertDepartment(){
        adminMapper.insertDepartment();
    }
    public void updateDepartment(){
        adminMapper.updateDepartment();
    }
    public void deleteDepartment(){
        adminMapper.deleteDepartment();
    }
}
