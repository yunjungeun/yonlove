package com.example.yoonlove.mapper;

import com.example.yoonlove.dto.CreatorDto;
import com.example.yoonlove.dto.PageDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CreatorMapper {
    public List<CreatorDto> selectListCreator(PageDto dto);
    public PageDto totalCreatorPost(PageDto dto);
    public  CreatorDto selectCreator(CreatorDto creatorDto);
    public void insertCreator(CreatorDto creatorDto);
    public void updateCreator(CreatorDto creatorDto);
    public void deleteCreator(CreatorDto dto);

}
