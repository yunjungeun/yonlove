package com.example.yoonlove.service;

import com.example.yoonlove.dto.FileDto;
import com.example.yoonlove.dto.SceneDto;
import com.example.yoonlove.mapper.FileMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class FileService {

    @Autowired
    private FileMapper fileMapper;



    public ResponseEntity<String> uploadFile(MultipartFile file, String path)throws IOException{
        String savePath = path + "/src/main/resources/static/img/"+file.getOriginalFilename();

        int lastScenePkNum = fileMapper.lastScenePkNum();
        System.out.println("scene"+(lastScenePkNum+1));
        try {
            //file 테이블 처리
            FileDto fileDto = new FileDto();
            fileDto.setScene_id("scene"+(lastScenePkNum));  //파일 fk
            fileDto.setFile_name(file.getOriginalFilename());  //파일 이름
            fileDto.setFile_path(savePath);  //파일 경로
            fileDto.setFile_data(file.getBytes());  //파일크기
            fileMapper.insertFile(fileDto);
            //file 테이블 처리 end

            // 저장할 위치에 파일객체생성
            File dest = new File(savePath);
            file.transferTo(dest);    // 파일 저장

            return ResponseEntity.ok("파일 업로드 성공");
        } catch (IOException e) {
            // 파일 업로드 실패 시 에러 응답을 반환
            e.printStackTrace();
            return ResponseEntity.status(500).body("파일 업로드 실패");
        }
    }




    public void insertNull(int lastnum){
        SceneDto dto = new SceneDto();
        String result = "scene"+lastnum;
        dto.setScene_id(result);
        fileMapper.insertNull(dto);
    }


    @Transactional
    public void insertFile(MultipartFile file, int lastnum) throws IOException {

        if(file.isEmpty()){
            System.out.println("test!!!");
        }

        if (file != null && !file.isEmpty()) {
            FileDto fileDto = new FileDto();
            fileDto.setScene_id("scene"+lastnum);
            fileDto.setFile_name(file.getOriginalFilename());
            fileDto.setFile_path("C:/1019/src/main/resources/static/img/" + file.getOriginalFilename());
            fileDto.setFile_data(file.getBytes());
            // 파일을 서버의 파일 시스템에 저장
            File destFile = new File("C:/1019/src/main/resources/static/img/" + file.getOriginalFilename());
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
            String uploadDirectory = "C:/1019/src/main/resources/static/img/";
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



    public void deleFile(SceneDto dto){

        FileDto existingFile = fileMapper.selectFile(dto);

        File file = new File(existingFile.getFile_path());
        if (file.exists()) {
            file.delete();
        }
    }

    public void deletdb(SceneDto dto){
        fileMapper.deleteFile(dto);
    }


}





