package com.example.yoonlove.mapper;

import com.example.yoonlove.dto.CreatorDto;
import com.example.yoonlove.dto.ProjectDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ProjectMapper {

    public List<ProjectDto> selectListProject();

    public ProjectDto selectProject(ProjectDto projectDto);

    public void insertProject(ProjectDto dto);

}