package com.example.yoonlove.mapper;

import com.example.yoonlove.dto.NoticeDto;
import com.example.yoonlove.dto.QnADto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CsMapper {
    //공지사항
    public NoticeDto selectNotice();
    public List<NoticeDto> selectListNotice();
    public void insertNotice();
    public void updateNotice();
    public void deleteNotice();

    public QnADto selectQnADto();
    public List<QnADto> selectListQnADto();
    public void insertQnADto();
    public void updateQnADto();
    public void deleteQnADto();
}
