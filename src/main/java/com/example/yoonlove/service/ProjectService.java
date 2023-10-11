package com.example.yoonlove.service;

import com.example.yoonlove.dto.CreatorDto;
import com.example.yoonlove.dto.ProjectDto;
import com.example.yoonlove.mapper.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProjectService {
    @Autowired
    private ProjectMapper projectMapper;

    public List<ProjectDto> selectListProject(){
        return projectMapper.selectListProject();
    }

    public ProjectDto selectProject(ProjectDto projectDto){
        return projectMapper.selectProject(projectDto);
    }

    public void insertProject(ProjectDto dto){
        projectMapper.insertProject(dto);
    }


    public void deleteProject(ProjectDto dto){ //게시글증가번호 = ch_id라서 creatorDto로 받음
        projectMapper.deleteProject(dto);
    }


    public void updateProject(ProjectDto projectDto){
        projectMapper.updateProject(projectDto);}

}
