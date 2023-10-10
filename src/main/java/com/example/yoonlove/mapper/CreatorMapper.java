package com.example.yoonlove.mapper;

import com.example.yoonlove.dto.CreatorDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CreatorMapper {
    public List<CreatorDto> selectListCreator();
    public  CreatorDto selectCreator(CreatorDto creatorDto);
    public void insertCreator(CreatorDto dto);
    public void updateCreator(CreatorDto creatorDto);
    public void deleteCreator(CreatorDto dto);

}
