package com.example.yoonlove.service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Service
public class CreatorService {
    //Dto 객체 주입
    @Autowired
    private CreatorDto creatorMapper;

    //서비스 메소드 작성 CRUD
    public CreatorDto selectCreator(){
        return creatorMapper.selectCreator();
    }

    public List<CreatorDto> selectListCreator(){
        return creatorMapper.selectListCreator();
    }
}
