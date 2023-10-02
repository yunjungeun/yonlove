package com.example.yoonlove.mapper;

import com.example.yoonlove.Dto.NoticeDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CsMapper {
    public List<NoticeDto> selectListNotice();
    public NoticeDto selectNotice();
    public void insertNotice();
    public void updateNotice();
    public void deleteNotice();
}
