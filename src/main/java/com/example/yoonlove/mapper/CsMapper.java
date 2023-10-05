package com.example.yoonlove.mapper;

import com.example.yoonlove.dto.NoticeDto;
import com.example.yoonlove.dto.QnADto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CsMapper {
    //공지사항
    public NoticeDto selectNotice(NoticeDto dto);
    public List<NoticeDto> selectListNotice();
    public void insertNotice(NoticeDto dto);
    public void updateNotice(NoticeDto dto);
    public void deleteNotice();

    public QnADto selectQnA();
    public List<QnADto> selectListQnA();
    public void insertQnA();
    public void updateQnA();
    public void deleteQnA();
}
