package com.ccic.salesapp.noncar.repository.basedb.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ccic.salesapp.noncar.repository.basedb.po.PlanMethod;

@Mapper
public interface PlanMethodMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PlanMethod record);

    int insertSelective(PlanMethod record);

    PlanMethod selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PlanMethod record);

    int updateByPrimaryKey(PlanMethod record);
    
    PlanMethod selectByPlanId(Integer planId);
}