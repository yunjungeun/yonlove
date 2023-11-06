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



/*
    public void  searchCh() throws IOException {
        // Define and execute the API request
        YouTube.Channels.List request = youTube.channels()
                .list(Collections.singletonList("id,snippet,statistics,status"));
        request.setKey("AIzaSyDYbGsMdpj9MqMiC2F6hFps-T-7jdXfJME"); // YouTube API 키 설정
        ChannelListResponse response = request.setId(Collections.singletonList("ytn")).execute();
        System.out.println(response);

        try {
            // Jackson ObjectMapper 생성
            ObjectMapper objectMapper = new ObjectMapper();
            String result = objectMapper.writeValueAsString(response);

            // JSON 문자열을 JsonNode로 파싱
            JsonNode jsonNode = objectMapper.readTree(result);
            // 검색된 채널 정보를 items 배열로 저장하고 그 중 첫 번째 검색된 채널의 "statistics" 객체에서 "subscriberCount" 값을 추출
            String title = jsonNode.get("items").get(0).get("snippet").get("title").asText();
            String chId = jsonNode.get("items").get(0).get("snippet").get("customUrl").asText();
            String videoCount = jsonNode.get("items").get(0).get("statistics").get("videoCount").asText();
            String subscriberCount = jsonNode.get("items").get(0).get("statistics").get("subscriberCount").asText();
            String viewCount = jsonNode.get("items").get(0).get("statistics").get("viewCount").asText();

            // 추출한 비디오 제목 출력
            System.out.println("채널명 : " + title);
            System.out.println("채널ID : " + chId);
            System.out.println("구독자 수: " + subscriberCount);
            System.out.println("업로드 영상 수 : " + videoCount);
            System.out.println("총 조회수 : " + viewCount);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
*/

}
