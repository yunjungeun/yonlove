package com.example.yoonlove.service;

import com.example.yoonlove.dto.FileDto;
import com.example.yoonlove.mapper.FileMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class FileService {

    @Autowired
    private FileMapper fileMapper;

    @Transactional
    public void uploadFile(MultipartFile file) throws IOException {


        if(file.isEmpty()){
            System.out.println("test!!!");
        }



        if (file != null && !file.isEmpty()) {
            FileDto fileDto = new FileDto();
            fileDto.setFile_name(file.getOriginalFilename());
            fileDto.setFile_path("C:/1019/files/abc.png" + file.getOriginalFilename());
            fileDto.setFile_data(file.getBytes());
            System.out.println(fileDto.toString());
            // 파일을 서버의 파일 시스템에 저장
            File destFile = new File("C:/1019/files/abc.png" + file.getOriginalFilename());
            file.transferTo(destFile);

            fileMapper.insertFile(fileDto);
        }

    }

    @Transactional
    public void updateFile(String file_id, MultipartFile newFile) throws IOException {
        // 기존 파일 정보 가져오기
        FileDto existingFile = fileMapper.getFileById(file_id);

        // 새로운 파일 정보 설정
        existingFile.setFile_name(newFile.getOriginalFilename());
        existingFile.setFile_path("C:/1019/files/abc.png" + newFile.getOriginalFilename());
        existingFile.setFile_data(newFile.getBytes());

        // 파일을 서버의 파일 시스템에 저장
        File destFile = new File("C:/1019/files/abc.png" + newFile.getOriginalFilename());
        newFile.transferTo(destFile);

        // 데이터베이스 업데이트
        fileMapper.updateFile(existingFile);
    }

}