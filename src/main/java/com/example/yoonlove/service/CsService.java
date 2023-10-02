package com.example.yoonlove.service;

import com.example.yoonlove.Dto.CreatorDto;
import com.example.yoonlove.Dto.NoticeDto;
import com.example.yoonlove.mapper.CsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CsService {
    @Autowired
    private CsMapper csMapper;

    //서비스 메소드 작성 CRUD
    public NoticeDto selectNotice(){
        return csMapper.selectNotice();
    }

    public List<NoticeDto> selectListNotice(){
        return csMapper.selectListNotice();
    }

    public void insertNotice(){
        csMapper.insertNotice();
    }
    public void updateNotice(){
        csMapper.updateNotice();
    }
    public void deleteNotice(){
        csMapper.deleteNotice();
    }

}
