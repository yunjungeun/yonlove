package com.example.yoonlove.mapper;

import com.example.yoonlove.dto.NoticeDto;
import com.example.yoonlove.dto.PageDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PageMapper {

    public PageDto totalPost(PageDto dto);

    //게시글 리스트  들어가야될 값 : table, id, 시작글, 끝 글
    public List<NoticeDto> postList(PageDto dto);

}
