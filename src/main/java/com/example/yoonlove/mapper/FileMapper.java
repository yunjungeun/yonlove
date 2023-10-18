package com.example.yoonlove.mapper;

import com.example.yoonlove.dto.FileDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;

@Mapper
public interface FileMapper {
    void insertFile(FileDto fileDto);

    // 파일 정보를 업데이트하는 쿼리
    void updateFile(FileDto fileDto);

    // 파일 정보를 삭제하는 쿼리
    void deleteFile(String file_id);

    // 파일 ID로 파일 정보를 조회하는 쿼리
    FileDto getFileById(String file_d);


}