package com.example.yoonlove.dto;

import lombok.Data;

@Data
public class CreatorDto {
    private String ch_id;
    private String ch_name;
    private int ch_sub;
    private String user_id;
    private String description;
    private int videocount;
    private long viewcount;
    private String createrurl;

    private String formattedVideocount;
    private String formattedViewcount;
    private String formattedCh_sub;
    }

