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

    public SceneDto selectScene(SceneDto dto) {
        return sceneMapper.selectScene(dto);
    }

    public List<SceneDto> selectListScene() {
        return sceneMapper.selectListScene();
    }


    public void insertScene(SceneDto dto) {


        sceneMapper.insertScene(dto);

    }















    public void updateScene(SceneDto dto){
        sceneMapper.updateScene(dto);
    }
    public void deleteScene(SceneDto dto){
        sceneMapper.deleteScene(dto);
    }

    //출연자정보
    public ActorDto selectActor(ActorDto dto){
        return sceneMapper.selectActor(dto);
    }
    public List<ActorDto> selectListActor() {
        return sceneMapper.selectListActor();
    }
    public void insertActor(ActorDto dto){
        sceneMapper.insertActor(dto);
    }
    public void updateActor(ActorDto dto){
        sceneMapper.updateActor(dto);
    }
    public void deleteActor(ActorDto dto){
        sceneMapper.deleteActor(dto);
    }
}
