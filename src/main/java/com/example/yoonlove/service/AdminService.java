package com.example.yoonlove.service;

import com.example.yoonlove.dto.CompanyDto;
import com.example.yoonlove.dto.DepartmentDto;
import com.example.yoonlove.dto.PageDto;
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
    public UserDto selectUser(UserDto dto){
        return adminMapper.selectUser(dto);
    }

    public List<UserDto> selectListUser(PageDto pageInfo) {
        return adminMapper.selectListUser(pageInfo);
    }


    public void insertUser(){
        adminMapper.insertUser();
    }
    public void updateUser(UserDto dto){
        adminMapper.updateUser(dto);
    }
    public void deleteUser(UserDto dto){
        adminMapper.deleteUser(dto);
    }

    //부서정보
    public DepartmentDto selectDepartment(DepartmentDto dto){
        return adminMapper.selectDepartment(dto);
    }
    public List<DepartmentDto> selectListDepartment(PageDto dto) {
        return adminMapper.selectListDepartment(dto);
    }
    public void insertDepartment(DepartmentDto dto){
        adminMapper.insertDepartment(dto);
    }
    public void updateDepartment(DepartmentDto dto){
        adminMapper.updateDepartment(dto);
    }
    public void deleteDepartment(DepartmentDto dto){
        adminMapper.deleteDepartment(dto);
    }

    //회사정보
    public CompanyDto selectCompany(CompanyDto dto){return adminMapper.selectCompany(dto); }

}
