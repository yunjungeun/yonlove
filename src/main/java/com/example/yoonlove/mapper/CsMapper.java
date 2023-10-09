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
    public void deleteNotice(NoticeDto dto);
    public void NoticeCnt(NoticeDto dto);

    public QnADto selectQnA(QnADto dto);
    public List<QnADto> selectListQnA();
    public void insertQnA(QnADto dto);
    public void updateQnA(QnADto dto);
    public void deleteQnA(QnADto dto);
    public void QnACnt(QnADto dto);
}
