package com.example.yoonlove.service;


import com.example.yoonlove.dto.BestVideoDto;
import com.example.yoonlove.dto.PageDto;
import com.example.yoonlove.dto.UserDto;
import com.example.yoonlove.dto.VideoDto;
import com.example.yoonlove.mapper.VideoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;


@Service
public class VideoService {

    @Autowired
    private VideoMapper videoMapper;
    @Autowired
    private YouTubeService youTubeService;
    @Autowired
    private UserService userService;

    public List<VideoDto> selectListContent(PageDto pageInfo, String ch_id) {
        List<VideoDto> videoList = videoMapper.selectListContent(pageInfo);

        //날짜 변환
        DecimalFormat decimalFormat = new DecimalFormat("#,###");

        //데이터를 내가 원하는 포멧으로 변경 저장
        for(int i = 0; i<videoList.size(); i++){
            String formattedView = decimalFormat.format(videoList.get(i).getVideo_view());
            String formattedLike = decimalFormat.format(videoList.get(i).getLike_cnt());
            String formattedDate =videoList.get(i).getUpload_date().substring(0,10);
            String formattedComment =decimalFormat.format(videoList.get(i).getCommentcnt());

            videoList.get(i).setFormattedDate(formattedDate);
            videoList.get(i).setFormattedView(formattedView);
            videoList.get(i).setFormattedLike(formattedLike);
            videoList.get(i).setFormattedComment(formattedComment);
        }

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
                dto.setCh_id(channelId);
                insertContent(dto);
            }
        }

    }



    public void bestvideo(String userId){
        UserDto userInfo = userService.getUser(userId);
        BestVideoDto bestVideoDto = new BestVideoDto();

        List<VideoDto> videoDtos = videoMapper.searchVideo(userInfo.getCompany_id());
        System.out.println(videoDtos);

    }
}