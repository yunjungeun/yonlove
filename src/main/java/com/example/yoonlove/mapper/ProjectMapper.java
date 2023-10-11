package com.example.yoonlove.mapper;

import com.example.yoonlove.dto.BudgetDto;
import com.example.yoonlove.dto.CreatorDto;
import com.example.yoonlove.dto.ProjectDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ProjectMapper {

    public List<ProjectDto> selectListProject();

    public ProjectDto selectProject(ProjectDto projectDto);

    public void insertProject(ProjectDto dto);

    public void deleteProject(ProjectDto dto);

    public void updateProject(ProjectDto projectDto);
// -------------------------프로젝트 끝

    public List<BudgetDto> selectListBudget();
    public BudgetDto selectBudget(BudgetDto budgetDto);
    public void insertBudget(BudgetDto dto);
    public void updateBudget(BudgetDto budgetDto);
    public void deleteBudget(BudgetDto dto);



}