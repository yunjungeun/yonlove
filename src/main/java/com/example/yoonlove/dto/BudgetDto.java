package com.example.yoonlove.dto;

import lombok.Data;

@Data
public class BudgetDto {
    //제작예산
    private String budget_id;
    private String budget_name;
    private String budget_content;
    private int budget_amount;
    private String budget_code;
    private String budget_flag;
    private String project_id;
    //숫자를 20,000 처럼 금액으로 바꾼값을 저장하기위한 프로퍼티
    private String fomattedAmount;
   //조인 테이블 프로퍼티 / 테이블명 : budgetjoin
    private String project_name;
}
