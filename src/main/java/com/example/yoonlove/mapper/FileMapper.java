package com.example.yoonlove.mapper;

import com.example.yoonlove.dto.FileDto;
import com.example.yoonlove.dto.SceneDto;
import com.example.yoonlove.dto.ScriptPaperDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileMapper {
    void insertFile(FileDto fileDto);

    //첨부파일이 null일때
    void insertNull(SceneDto dto);
    // 파일 정보를 업데이트하는 쿼리
    void updateFile(FileDto fileDto);

    // 파일 정보를 삭제하는 쿼리
    /*void deleteFile(String file_id);*/

    // 파일 ID로 파일 정보를 조회하는 쿼리
    FileDto getFileById(String file_id);

    //fk값으로 파일테이블에서 파일정보를 가저오는 메소드 start
    FileDto selectSceneFile(SceneDto dto);
    FileDto selectScriptFile(ScriptPaperDto dto);
//fk값으로 파일테이블에서 파일정보를 가저오는 메소드 end

    void deleteFile(String fk, String fkId);

    //마지막 scene테이블의 pk를 찾는 메소드
    int lastScenePkNum();

    //마지막 script 테이블의 pk를 찾는 메소드
    int lastScriptPkNum();

    FileDto searchFk(String fk, String fkId);

}