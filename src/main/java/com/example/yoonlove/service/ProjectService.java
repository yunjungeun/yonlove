package com.example.yoonlove.service;

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

}
