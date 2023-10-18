package com.example.yoonlove.service;

import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.YouTubeScopes;
import com.google.api.services.youtube.model.SearchListResponse;

import java.io.IOException;

public class YouTubeService {

    private final YouTube youTube;

    public YouTubeService(String apiKey) {
        youTube = new YouTube.Builder(
                new NetHttpTransport(),
                new JacksonFactory(),
                request -> {
                })
                .setApplicationName("YourAppName")
                .build();
    }

    public SearchListResponse searchVideos(String query) throws IOException {
        YouTube.Search.List search = youTube.search().list("id,snippet");
        search.setKey(apiKey);
        search.setQ(query);
        search.setType("video");

        return search.execute();
    }
}
