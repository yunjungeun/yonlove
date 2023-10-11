package com.example.yoonlove.mapper;

import com.example.yoonlove.dto.PageDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PageMapper {

    public PageDto totalPost(PageDto dto);
}
