package com.example.yoonlove.controller;

import com.example.yoonlove.mapper.FileMapper;
import com.example.yoonlove.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class UploadFileController {

    @Autowired
    private FileService fileService;
    @Autowired
    private FileMapper fileMapper;

    @PostMapping("/upload")
    @ResponseBody
    public ResponseEntity<String> fileUpload(@RequestParam("file") MultipartFile file, String table) throws IOException {
        String basePath = "C:/Users/강의실3_PC014/IdeaProjects/yonlove";

        return fileService.uploadFile(file,basePath, table);
    }

    @PostMapping("/updateFile")
    @ResponseBody
    public ResponseEntity<String> updateFile(@RequestParam("newfile") MultipartFile newfile,
                                             @RequestParam("fk") String fk) throws IOException {

        String basePath = "C:/Users/강의실3_PC014/IdeaProjects/yonlove";
        return fileService.updateFile(newfile, basePath, fk);
    }

    @PostMapping("/deleteFile")
    @ResponseBody
    public ResponseEntity<String> deleteFile(@RequestParam String fk) throws IOException {
        System.out.println(fk);
        String basePath = "C:/Users/강의실3_PC014/IdeaProjects/yonlove";
        return fileService.deleteFile(basePath, fk);
    }
}
