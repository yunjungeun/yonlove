package com.example.yoonlove.mapper;

import com.example.yoonlove.dto.PageDto;
import com.example.yoonlove.dto.VideoDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VideoMapper {


    public List<VideoDto> selectListContent(PageDto pageInfo);

    public PageDto totalContentPost(PageDto dto);
    public VideoDto selectContent(VideoDto dto);

    public void insertContent(VideoDto dto);

    public void updateContent(VideoDto dto);

    //id가 이미 존재하는 확인하는 메소드
    VideoDto searchVideoId(String video_id);
    public void deleteContent(VideoDto dto);

}