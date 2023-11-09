package com.example.yoonlove.service;


import com.example.yoonlove.dto.PageDto;
import com.example.yoonlove.dto.VideoDto;
import com.example.yoonlove.mapper.VideoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.List;


@Service
public class VideoService {

    @Autowired
    private VideoMapper videoMapper;
    @Autowired
    private YouTubeService youTubeService;


    public List<VideoDto> selectListContent(PageDto pageInfo) {
        List<VideoDto> videoList = videoMapper.selectListContent(pageInfo);// 오류원인:videoMapper 얘를 VideoMapper로 했었음..
        return videoList;
    }


    public VideoDto selectContent(VideoDto videodto) {
        VideoDto dto = videoMapper.selectContent(videodto);
        return dto;
    }

    public void insertContent(VideoDto dto) {
        videoMapper.insertContent(dto);
    }

    public Boolean searchVideoId(String video_id){
        if(videoMapper.searchVideoId(video_id) == null){
            return true;
        } else {
            return false;
        }
    }

    public void updateContent(VideoDto dto) {
        videoMapper.updateContent(dto);
    }

    public void deleteContent(VideoDto dto) {
        videoMapper.deleteContent(dto);
    }

    //채널 id만으로 채널의 최근영상 5개를 불러와서 vdieo테이블에 저장함.
    public void videoInsert(String channelId) throws GeneralSecurityException, IOException {
        HashMap<String, String> videosInfo = youTubeService.getVideosInfo(channelId);

        for(int i = 0; i < videosInfo.size(); i++){
            VideoDto dto = new VideoDto();

            // 값이 null일 때 반복문 종료시키고 sql 실행하는걸 막음
            if (videosInfo.get("id" + i) == null) {
                break;
            }

            if(searchVideoId(videosInfo.get("id"+i))) {
                dto.setVideo_id(videosInfo.get("id" + i));  //영상id
                dto.setVideo_name(videosInfo.get("title" + i)); //영상제목
                dto.setLike_cnt(Integer.parseInt(videosInfo.get("likeCount" + i)));  //좋아요 수
                dto.setVideo_view(Integer.parseInt(videosInfo.get("viewCount" + i)));  //조회수
                dto.setVideo_url("https://www.youtube.com/watch?v=" + videosInfo.get("id" + i));  //동영상 url
                dto.setUpload_date(videosInfo.get("uploadDate" + i));  //업로드일
                dto.setCommentcnt(Integer.parseInt(videosInfo.get("commentCount" + i)));  //댓글 수
                insertContent(dto);
            }
        }

    }
}