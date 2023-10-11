package com.example.yoonlove.service;

import com.example.yoonlove.dto.BudgetDto;
import com.example.yoonlove.dto.CreatorDto;
import com.example.yoonlove.dto.ProjectDto;
import com.example.yoonlove.mapper.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProjectService {
    @Autowired
    private ProjectMapper projectMapper;

    public List<ProjectDto> selectListProject(){
        return projectMapper.selectListProject();}

    public ProjectDto selectProject(ProjectDto projectDto){
        return projectMapper.selectProject(projectDto);}

    public void insertProject(ProjectDto dto){
        projectMapper.insertProject(dto);}

    public void deleteProject(ProjectDto dto){ //게시글증가번호 = ch_id라서 creatorDto로 받음
        projectMapper.deleteProject(dto);}

    public void updateProject(ProjectDto projectDto){
        projectMapper.updateProject(projectDto);}

    // 프로젝트,기획 부분 끝 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public List<BudgetDto> selectListBudget(){
        return projectMapper.selectListBudget();}

    public BudgetDto selectBudget(BudgetDto budgetDto){
        return projectMapper.selectBudget(budgetDto);}
    public void insertBudget(BudgetDto dto){
        projectMapper.insertBudget(dto);}

    public void deleteBudget(BudgetDto dto){
        projectMapper.deleteBudget(dto);}

    public void updateBudget(BudgetDto budgetDto){
        projectMapper.updateBudget(budgetDto);}

}
