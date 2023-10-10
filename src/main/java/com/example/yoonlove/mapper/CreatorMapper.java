package com.example.yoonlove.mapper;

import com.example.yoonlove.dto.CreatorDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CreatorMapper {
    public List<CreatorDto> selectListCreator();
    CreatorDto selectCreator();
    public void insertCreator(CreatorDto dto);
    CreatorDto updateCreator();
    CreatorDto deleteCreator();

}
