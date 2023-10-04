package com.example.yoonlove.service;

import com.example.yoonlove.dto.ActorDto;
import com.example.yoonlove.dto.SceneDto;
import com.example.yoonlove.mapper.SceneMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SceneService {
    @Autowired
    private SceneMapper sceneMapper;

    public SceneDto selectScene(){
        return sceneMapper.selectScene();
    }
    public List<SceneDto> selectListScene() {
        return sceneMapper.selectListScene();
    }
    public void insertScene(){
        sceneMapper.insertScene();
    }
    public void updateScene(){
        sceneMapper.updateScene();
    }
    public void deleteScene(){
        sceneMapper.deleteScene();
    }

    //출연자정보
    public ActorDto selectActor(){
        return sceneMapper.selectActor();
    }
    public List<ActorDto> selectListActor() {
        return sceneMapper.selectListActor();
    }
    public void insertActor(){
        sceneMapper.insertActor();
    }
    public void updateActor(){
        sceneMapper.updateActor();
    }
    public void deleteActor(){
        sceneMapper.deleteActor();
    }
}
