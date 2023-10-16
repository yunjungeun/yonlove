package com.example.yoonlove.dto.commandobj;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BudgetSearchDto {
    private String name;
    private String projectId;
    //검색기능- DB 에서 이름과 아이디를 찾기위해 따로 DTO를 만듬
}
