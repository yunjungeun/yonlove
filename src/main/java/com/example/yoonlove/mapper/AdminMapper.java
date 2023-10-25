package com.example.yoonlove.mapper;

import com.example.yoonlove.dto.CompanyDto;
import com.example.yoonlove.dto.DepartmentDto;
import com.example.yoonlove.dto.PageDto;
import com.example.yoonlove.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {
    //고객정보
    public List<UserDto> selectListUser(PageDto pageInfo);
    public PageDto totalUserPost(PageDto dto);
    public UserDto selectUser(UserDto dto);
    public void insertUser();
    public void updateUser(UserDto dto);
    public void deleteUser(UserDto dto);

    //부서정보
    public List<DepartmentDto> selectListDepartment(PageDto dto);
    public DepartmentDto selectDepartment(DepartmentDto dto);
    public void insertDepartment(DepartmentDto dto);
    public void updateDepartment(DepartmentDto dto);
    public void deleteDepartment(DepartmentDto dto);
    public PageDto totalDptPost(PageDto dto);


    //회사정보조회
    public CompanyDto selectCompany(CompanyDto dto);
}
