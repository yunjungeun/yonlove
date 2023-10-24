package com.example.yoonlove.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BudgetDto {
    //제작예산
    private String budget_id;
    private String budget_name;
    private String budget_content;
    private int budget_amount;
    private String budget_code;
    private String budget_flag;
    private String project_id;

    @Override
    public String toString() {
        return "BudgetDto{" +
                "budget_id='" + budget_id + '\'' +
                ", budget_name='" + budget_name + '\'' +
                ", budget_content='" + budget_content + '\'' +
                ", budget_amount=" + budget_amount +
                ", budget_code='" + budget_code + '\'' +
                ", budget_flag='" + budget_flag + '\'' +
                ", project_id='" + project_id + '\'' +
                '}';
    }
}
