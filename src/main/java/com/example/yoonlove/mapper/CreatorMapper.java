package com.example.yoonlove.mapper;

import java.util.List;

@Mapper
public interface CreatorMapper {
    List<CreatorDto> selectListCreator();
    CreatorDto selectCreator();
    CreatorDto insertCreator();
    CreatorDto updateCreator();
    CreatorDto deleteCreator();

}
