package com.ccic.salesapp.noncar.repository.basedb.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ccic.salesapp.noncar.dto.salesplan.PlanChild;
import com.ccic.salesapp.noncar.repository.basedb.po.SalesPlanChild;

@Mapper
public interface SalesPlanChildMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SalesPlanChild record);
    
    int insert2(PlanChild record);

    int insertSelective(SalesPlanChild record);

    SalesPlanChild selectByPrimaryKey(Integer id);
    
    List<PlanChild> selectByPrimaryKey2(Integer id);

    int updateByPrimaryKeySelective(SalesPlanChild record);

    int updateByPrimaryKey(SalesPlanChild record);
    
    SalesPlanChild selectByChildPlanCode(Map map);
    
    List<String> selectInsuredTypeListByChildPlanId(Integer childPlanId);
}