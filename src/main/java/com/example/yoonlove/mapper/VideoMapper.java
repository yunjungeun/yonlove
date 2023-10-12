package com.example.yoonlove.mapper;

import com.example.yoonlove.dto.VideoDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VideoMapper {


    public List<VideoDto> selectListContent();


    public VideoDto selectContent(VideoDto dto);

    public void insertContent(VideoDto dto);

    public void updateContent(VideoDto dto);
    public void deleteContent(VideoDto dto);

}