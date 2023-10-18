package com.example.yoonlove.service;

import org.springframework.stereotype.Service;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;

import java.io.IOException;

@Service
public class YouTubeService {
    private final YouTube youTube;

    public YouTubeService() {
        // YouTube 객체 초기화 (인증 정보 설정이나 필요한 경우 여기서 설정)
        this.youTube = new YouTube.Builder(
                new com.google.api.client.http.javanet.NetHttpTransport(),
                new com.google.api.client.json.gson.GsonFactory(),
                null
        ).setApplicationName("YourAppName").build();
    }

    public SearchListResponse searchVideos(String query) throws IOException {
        YouTube.Search.List search = youTube.search().list("id,snippet");
        search.setKey("YOUR_YOUTUBE_API_KEY"); // YouTube API 키 설정
        search.setQ(query);
        search.setType("video");

        return search.execute();
    }
}
