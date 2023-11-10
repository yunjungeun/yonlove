package com.example.yoonlove.dto;

import lombok.Data;

@Data
public class BestVideoDto {
    private String bestViewVideoName;
    private String bestViewVideoUrl;
    private String bestViewChName;

    private String bestLikeChName;
    private String bestLikeVideoName;
    private String bestLikeVideoUrl;

    private String bestSubscribeChName;
    private String bestSubscribeVideoName;
    private String bestSubscribeVideoUrl;
}
