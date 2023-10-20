package com.example.yoonlove.service;

import com.example.yoonlove.dto.FileDto;
import com.example.yoonlove.dto.SceneDto;
import com.example.yoonlove.mapper.FileMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileService {

    @Autowired
    private FileMapper fileMapper;




    @Transactional
    public void insertFile(MultipartFile file, int lastnum) throws IOException {


        if(file.isEmpty()){
            System.out.println("test!!!");
        }



        if (file != null && !file.isEmpty()) {
            FileDto fileDto = new FileDto();
            fileDto.setScene_id("scene"+lastnum);
            fileDto.setFile_name(file.getOriginalFilename());
            fileDto.setFile_path("C:/1019/files/abc.png" + file.getOriginalFilename());
            fileDto.setFile_data(file.getBytes());
            // 파일을 서버의 파일 시스템에 저장
            File destFile = new File("C:/1019/files/abc.png" + file.getOriginalFilename());
            file.transferTo(destFile);

            fileMapper.insertFile(fileDto);
        }

    }

    @Transactional



    public void updateFile(SceneDto dto, MultipartFile newFile) throws IOException {
        // 1. 기존 파일 정보 가져오기
        FileDto existingFile = fileMapper.selectFile(dto);

        // 2. 새로운 파일 정보 설정
        if (newFile != null && !newFile.isEmpty()) {
            existingFile.setFile_name(newFile.getOriginalFilename());

            // 파일 업로드 경로 설정
            String uploadDirectory = "C:/1019/files/";
            String newFilePath = uploadDirectory + existingFile.getFile_id() + "_" + newFile.getOriginalFilename();
            existingFile.setFile_path(newFilePath);
            existingFile.setFile_data(newFile.getBytes());

            // 3. 기존 파일 삭제
            File file = new File(existingFile.getFile_path());
            if (file.exists()) {
                file.delete();
            }

            // 4. 새 파일 저장
            File destFile = new File(newFilePath);
            newFile.transferTo(destFile);

            // 5. 데이터베이스 업데이트
            fileMapper.updateFile(existingFile);
        }
    }

    //fk값 가지고 파일dto를 가저오는 메소드
    public FileDto selectFile(SceneDto dto){
        return fileMapper.selectFile(dto);
    };






}