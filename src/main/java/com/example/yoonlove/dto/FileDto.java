package com.example.yoonlove.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class FileDto {

    private String file_id;
    private String file_name;
    private String file_path;
    private byte[] file_data;
    private String scene_id;

    //스크립트 업로드파일에 대한 정보 추가
    private String script_id;
}
