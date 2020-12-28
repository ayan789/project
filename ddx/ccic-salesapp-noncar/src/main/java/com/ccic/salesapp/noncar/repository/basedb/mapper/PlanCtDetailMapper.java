package com.ccic.salesapp.noncar.repository.basedb.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import com.ccic.salesapp.noncar.repository.basedb.po.PlanCtDetail;

@Mapper
public interface PlanCtDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PlanCtDetail record);

    PlanCtDetail selectByPrimaryKey(Integer id);

    List<PlanCtDetail> selectAll();

    int updateByPrimaryKey(PlanCtDetail record);
    
    @MapKey("clauseCode")
    Map<String,PlanCtDetail> selectClausePropertyByPlanId(Integer  planId);
}