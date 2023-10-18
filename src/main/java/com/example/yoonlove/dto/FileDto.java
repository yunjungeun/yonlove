package com.example.yoonlove.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class FileDto {

    private String file_id;
    private String file_name;
    private String file_path;
    private byte[] file_data;
    /*private String scene_id;
    private String scenario_id;*/
}
