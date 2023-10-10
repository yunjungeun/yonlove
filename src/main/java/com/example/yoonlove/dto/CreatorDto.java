package com.example.yoonlove.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatorDto {
    private String ch_id;
    private String ch_name;
    private int ch_sub;
    private String user_id;

    @Override
    public String toString() {
        return "CreatorDto{" +
                "ch_id='" + ch_id + '\'' +
                ", ch_name='" + ch_name + '\'' +
                ", ch_sub=" + ch_sub +
                ", user_id='" + user_id + '\'' +
                '}';
    }
}
