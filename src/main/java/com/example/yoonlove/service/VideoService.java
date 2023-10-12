package com.example.yoonlove.service;


import com.example.yoonlove.dto.VideoDto;
import com.example.yoonlove.mapper.VideoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class VideoService {

    @Autowired
    private VideoMapper videoMapper;


    public List<VideoDto> selectListContent() {
        List<VideoDto> videoList = videoMapper.selectListContent();// 오류원인:videoMapper 얘를 VideoMapper로 했었음..
        return videoList;
    }

    public VideoDto selectContent(VideoDto videodto) {
        VideoDto dto = videoMapper.selectContent(videodto);
        return dto;
    }

    public void insertContent(VideoDto dto) {
        videoMapper.insertContent(dto);
    }

    public void updateContent(VideoDto dto) {
        videoMapper.updateContent(dto);

    }

    public void deleteContent(VideoDto dto) {
        videoMapper.deleteContent(dto);

    }







}