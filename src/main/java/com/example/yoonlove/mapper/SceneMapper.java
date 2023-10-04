package com.example.yoonlove.mapper;

import com.example.yoonlove.dto.ActorDto;
import com.example.yoonlove.dto.SceneDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SceneMapper {
    //스토리보드
    public List<SceneDto> selectListScene();
    public SceneDto selectScene();
    public void insertScene();
    public void updateScene();
    public void deleteScene();

    //출연자편성
    public List<ActorDto> selectListActor();
    public ActorDto selectActor();
    public void insertActor();
    public void updateActor();
    public void deleteActor();
}
