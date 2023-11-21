package com.example.yoonlove.service;

import com.example.yoonlove.dto.CreatorDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ChannelListResponse;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.VideoListResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.*;

@Service
public class YouTubeService {

    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private String key = "AIzaSyDYbGsMdpj9MqMiC2F6hFps-T-7jdXfJME";

    public static YouTube getService() throws GeneralSecurityException, IOException {
        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        return new YouTube.Builder(httpTransport, JSON_FACTORY, null)
                .setApplicationName("APPLICATION_NAME")
                .build();
    }

    //검색어로 채널의 id검색
    public ArrayList<HashMap<String, String>> searchId(String searchKeyword) throws GeneralSecurityException, IOException, GoogleJsonResponseException {
        YouTube youtubeService = getService();
        YouTube.Search.List request = youtubeService.search()
                .list(Collections.singletonList("snippet"));
        SearchListResponse response = request.setKey(key)
                .setMaxResults(5L)
                .setQ(searchKeyword)
                .setType(Collections.singletonList("channel"))
                .execute();  //검색결과


        //for문 결과값을 저장할 json 객체생성
        ArrayList<HashMap<String, String>> resultArray = new ArrayList<>();
        // 'items' 배열 가져오기
        JSONObject jsonObject = new JSONObject(response);
        JSONArray itemsArray = jsonObject.getJSONArray("items");

        // 'items' 배열 순회
        for (int i = 0; i < itemsArray.length(); i++) {
            JSONObject item = itemsArray.getJSONObject(i);

            // 필요한 값 추출 하고 해쉬맵에 저장
            String channelId = item.getJSONObject("snippet").getString("channelId");
            String channelTitle = item.getJSONObject("snippet").getString("channelTitle");
            String thumbnailsUrl = item.getJSONObject("snippet").getJSONObject("thumbnails")
                    .getJSONObject("default").getString("url");

            // HashMap에 저장
            HashMap<String, String> itemMap = new HashMap<>();
            itemMap.put("channelId", channelId);
            itemMap.put("channelTitle", channelTitle);
            itemMap.put("thumbnailsUrl", thumbnailsUrl);

            // 결과를 resultArray에 추가
            resultArray.add(itemMap);
        }
        return resultArray;
    }

    // 채널검색
    public CreatorDto channel(String channelId)  throws GeneralSecurityException, IOException, GoogleJsonResponseException {
        YouTube youtubeService = getService();
        YouTube.Channels.List request = youtubeService.channels()
                .list(Collections.singletonList("snippet,statistics"));
        ChannelListResponse response = request.setKey(key)
                .setId(Collections.singletonList(channelId)).execute();

        // Jackson ObjectMapper를 사용하여 JSON 파싱
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(String.valueOf(response));

        // 필드 값을 가져와서 사용
        Map<String, String> resultMap = new LinkedHashMap<>();
        resultMap.put("customUrl", jsonNode.get("items").get(0).get("snippet").get("customUrl").asText());
        resultMap.put("description", jsonNode.get("items").get(0).get("snippet").get("description").asText());
        resultMap.put("subscriberCount", jsonNode.get("items").get(0).get("statistics").get("subscriberCount").asText());
        resultMap.put("videoCount", jsonNode.get("items").get(0).get("statistics").get("videoCount").asText());
        resultMap.put("viewCount", jsonNode.get("items").get(0).get("statistics").get("viewCount").asText());

        //만들어낸 해쉬맵을 dto에 바인드
        CreatorDto dto = new CreatorDto();
        dto.setCh_sub(Integer.parseInt(resultMap.get("subscriberCount")));
        dto.setCh_id(channelId);
        dto.setCreaterurl("https://www.youtube.com/"+ resultMap.get("customUrl"));
        dto.setDescription(resultMap.get("description"));
        dto.setVideocount(Integer.parseInt(resultMap.get("videoCount")));
        dto.setViewcount(Long.parseLong(resultMap.get("viewCount")));

        return dto;
    }


    //특정 채널의 최신 동영상 5개를 가저오는 메소드
    public String getChannelVideos(String channelId) throws GeneralSecurityException, IOException{
        YouTube youtubeService = getService();
        YouTube.Search.List request = youtubeService.search()
                .list(Collections.singletonList("snippet"));
        SearchListResponse response = request.setKey(key)
                .setChannelId(channelId)
                .setMaxResults(5L)
                .setOrder("date")
                .setType(Collections.singletonList("video"))
                .execute();


        // Jackson ObjectMapper를 사용하여 JSON 파싱
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(String.valueOf(response));
        JsonNode items = jsonNode.get("items");


        StringBuilder videoIdsBuild = new StringBuilder();  //문자열을 연결할때 쓰는 객체
        for(int i = 0; i < items.size() ; i++) {
            videoIdsBuild.append(items.get(i).get("id").get("videoId").asText());
            if (i < items.size() - 1) {
                videoIdsBuild.append(",");
            }
        }
        return videoIdsBuild.toString();
    }


    //영상의 댓글, 구독, 좋아요 제목, 업로드 날짜 정보를 가져오는 메소드
    public HashMap<String, String> getVideosInfo(String channelId) throws GeneralSecurityException, IOException {
        String videoIds = getChannelVideos(channelId);

        YouTube youtubeService = getService();
        YouTube.Videos.List request = youtubeService.videos()
                .list(Collections.singletonList("snippet,statistics"));
        VideoListResponse response = request.setKey(key)
                .setId(Collections.singletonList(videoIds)).execute();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(String.valueOf(response));

        HashMap<String, String> resultMap = new LinkedHashMap<>();
        JsonNode items = jsonNode.get("items");

        for (int i = 0; i < items.size(); i++){
            String title = items.get(i).get("snippet").get("title").asText();
            String publishedAt  = items.get(i).get("snippet").get("publishedAt").asText();
            String id = items.get(i).get("id").asText();
            String viewCount = items.get(i).get("statistics").get("viewCount").asText();
            String likeCount = items.get(i).get("statistics").get("likeCount").asText();
            String commentCount = items.get(i).get("statistics").get("commentCount").asText();

            resultMap.put("title" + i, title);
            resultMap.put("uploadDate" + i, publishedAt);
            resultMap.put("id" + i, id);
            resultMap.put("viewCount" + i, viewCount);
            resultMap.put("likeCount" + i, likeCount);
            resultMap.put("commentCount" + i, commentCount);
        }
        return resultMap;
    }
}
