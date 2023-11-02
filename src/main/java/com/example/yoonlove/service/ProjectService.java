package com.example.yoonlove.service;

import com.example.yoonlove.dto.*;
import com.example.yoonlove.mapper.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProjectService {
    @Autowired
    private ProjectMapper projectMapper;

    public List<ProjectDto> selectListProject(PageDto pageDto){
        return projectMapper.selectListProject(pageDto);}

    public ProjectDto selectProject(ProjectDto projectDto){
        return projectMapper.selectProject(projectDto);}

    public void insertProject(ProjectDto dto){
        projectMapper.insertProject(dto);}

    public void deleteProject(ProjectDto dto){ //게시글증가번호 = ch_id라서 creatorDto로 받음
        projectMapper.deleteProject(dto);}

    public void updateProject(ProjectDto projectDto){
        projectMapper.updateProject(projectDto);}
    // ==============================프로젝트,기획 부분 끝 !!!!!!!!!!!!!!!!!!!!!!!!!!!
    public List<BudgetDto> selectListBudget(PageDto pageDto){
        return projectMapper.selectListBudget(pageDto);}

    public BudgetDto selectBudget(BudgetDto budgetDto){
        return projectMapper.selectBudget(budgetDto);}
    public void insertBudget(BudgetDto dto){
        projectMapper.insertBudget(dto);}

    public void deleteBudget(BudgetDto dto){
        projectMapper.deleteBudget(dto);}

    public void updateBudget(BudgetDto dto){
        projectMapper.updateBudget(dto);}

    //예산총액 구하는 메서드
    public int getTotalBudget(PageDto dto){
        return projectMapper.getTotalBudget(dto);
    }
    // ============================== Budget  끝 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    public List<ProduceDto> selectListProduce(PageDto pageDto){
        return projectMapper.selectListProduce(pageDto);}
    public void insertProduce(ProduceDto dto){
       projectMapper.insertProduce(dto);}
    public ProduceDto selectProduce(ProduceDto produceDto){
        return projectMapper.selectProduce(produceDto);}
    public void deleteProduce(ProduceDto dto){
         projectMapper.deleteProduce(dto);}

    public void updateProduce(ProduceDto dto){
        projectMapper.updateProduce(dto);}
    // ============================== 프로듀스 끝 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

}
