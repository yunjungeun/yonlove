package com.example.yoonlove.service;

import com.example.yoonlove.dto.NoticeDto;
import com.example.yoonlove.dto.PageDto;
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
    public NoticeDto selectNotice(NoticeDto dto){
        return csMapper.selectNotice(dto);
    }
    public List<NoticeDto> selectListNotice(PageDto dto){
        return csMapper.selectListNotice(dto);
    }
    public void insertNotice(NoticeDto dto){
        csMapper.insertNotice(dto);
    }
    public void updateNotice(NoticeDto dto){
        csMapper.updateNotice(dto);
    }
    public void deleteNotice(NoticeDto dto){
        csMapper.deleteNotice(dto);
    }
    public void NoticeCnt(NoticeDto dto){
        csMapper.NoticeCnt(dto);
    }

    //QnA
    public QnADto selectQnA(QnADto dto){
        return csMapper.selectQnA(dto);
    }
    public List<QnADto> selectListQnA(PageDto dto){
        return csMapper.selectListQnA(dto);
    }
    public void insertQnA(QnADto dto){
        csMapper.insertQnA(dto);
    }
    public void updateQnA(QnADto dto){
        csMapper.updateQnA(dto);
    }
    public void deleteQnA(QnADto dto){
        csMapper.deleteQnA(dto);
    }
    public void QnACnt(QnADto dto){ csMapper.QnACnt(dto);}
}
