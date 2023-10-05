package com.example.yoonlove.service;

import com.example.yoonlove.dto.NoticeDto;
import com.example.yoonlove.dto.QnADto;
import com.example.yoonlove.mapper.CsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CsService {
    @Autowired
    private CsMapper csMapper;

    //공지사항
    public NoticeDto selectNotice(){
        return csMapper.selectNotice();
    }
    public List<NoticeDto> selectListNotice(){
        return csMapper.selectListNotice();
    }
    public void insertNotice(NoticeDto dto){
        csMapper.insertNotice(dto);
    }
    public void updateNotice(){
        csMapper.updateNotice();
    }
    public void deleteNotice(){
        csMapper.deleteNotice();
    }

    //QnA
    public QnADto selectQnA(){
        return csMapper.selectQnA();
    }
    public List<QnADto> selectListQnA(){
        return csMapper.selectListQnA();
    }
    public void insertQnA(){
        csMapper.insertQnA();
    }
    public void updateQnA(){
        csMapper.updateQnA();
    }
    public void deleteQnA(){
        csMapper.deleteQnA();
    }
}
