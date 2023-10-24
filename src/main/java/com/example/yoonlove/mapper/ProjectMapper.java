package com.example.yoonlove.mapper;

import com.example.yoonlove.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ProjectMapper {

    public List<ProjectDto> selectListProject(PageDto pageDto);

    public PageDto totalProjectPost(PageDto dto);

    public ProjectDto selectProject(ProjectDto projectDto);

    public void insertProject(ProjectDto dto);

    public void deleteProject(ProjectDto dto);

    public void updateProject(ProjectDto projectDto);
// -------------------------====================================프로젝트 끝

    public List<BudgetDto> selectListBudget(PageDto pageDto );
    public PageDto totalBudgetPost(PageDto dto);
    public BudgetDto selectBudget(BudgetDto dto);
    public void insertBudget(BudgetDto dto);
    public void updateBudget(BudgetDto dto);
    public void deleteBudget(BudgetDto dto);
    // -------------------------====================================Budget 끝
    public List<ProduceDto> selectListProduce(PageDto pageDto);
    public PageDto totalProducePost(PageDto dto);
    public ProduceDto selectProduce(ProduceDto produceDto);
    public void insertProduce(ProduceDto produceDto);
    public void deleteProduce(ProduceDto dto);
    public void updateProduce(ProduceDto dto);
    // -------------------------================================프로듀스 끝
}