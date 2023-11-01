package com.example.yoonlove.service;

import com.example.yoonlove.dto.FileDto;
import com.example.yoonlove.dto.SceneDto;
import com.example.yoonlove.mapper.FileMapper;
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

    //파일 업로드처리
    public ResponseEntity<String> uploadFile(MultipartFile file, String path)throws IOException{
        //db에 저장할 상대경로
        String relativePath ="/static/img/"+file.getOriginalFilename();
        //실제 파일을 업로드할 절대경로 조립
        String savePath = path + "/src/main/resources" + relativePath;

        int lastScenePkNum = fileMapper.lastScenePkNum();
        try {
            //file 테이블 처리
            FileDto fileDto = new FileDto();
            fileDto.setScene_id("scene"+(lastScenePkNum));  //파일 fk
            fileDto.setFile_name(file.getOriginalFilename());  //파일 이름
            fileDto.setFile_path(relativePath);  //파일 상대경로
            fileDto.setFile_data(file.getBytes());  //파일크기

            System.out.println(fileDto.toString());
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


    public ResponseEntity<String> updateFile(MultipartFile newFile, String basePath, String fk) throws IOException {

        // 1. 기존 파일 정보 가져오기
        FileDto existingFileDto = fileMapper.searchFk(fk);

        //기존파일 절대경로 생성
        String absolutePath = basePath + "/src/main/resources" + existingFileDto.getFile_path();

        // 2. 새로운 파일 정보 설정
        if (newFile != null && !newFile.isEmpty()) {
            //새파일 이름을 dto에 수정저장
            existingFileDto.setFile_name(newFile.getOriginalFilename());

            // 새파일 상대 경로 설정
            String newFilePath ="/static/img/" + newFile.getOriginalFilename();
            existingFileDto.setFile_path(newFilePath);
            existingFileDto.setFile_data(newFile.getBytes());

            // 3. 기존 파일 삭제 (기존파일 절대경로)
            File existingFile = new File(absolutePath);
            if (existingFile.exists()) {
                existingFile.delete();
            }

            // 4. 새 파일 저장(절대경로 앞부분 + 새파일 상대경로)
            File destFile = new File(basePath +"/src/main/resources"+ newFilePath);
            newFile.transferTo(destFile);

            // 5. 데이터베이스 업데이트
            fileMapper.updateFile(existingFileDto);
            return ResponseEntity.ok("파일 업로드 성공");
        }
        return ResponseEntity.status(500).body("파일 업로드 실패");
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





