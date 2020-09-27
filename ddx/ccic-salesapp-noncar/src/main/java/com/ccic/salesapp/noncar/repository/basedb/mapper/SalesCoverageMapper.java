package com.ccic.salesapp.noncar.repository.basedb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ccic.salesapp.noncar.dto.salesplan.PlanCoverage;
import com.ccic.salesapp.noncar.repository.basedb.po.SalesCoverage;

@Mapper
public interface SalesCoverageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SalesCoverage record);

    int insertSelective(SalesCoverage record);
    
    int insert2(PlanCoverage record);

    SalesCoverage selectByPrimaryKey(Integer id);
    
    List<PlanCoverage> selectByChildPlanId(Integer id);

    int updateByPrimaryKeySelective(SalesCoverage record);

    int updateByPrimaryKey(SalesCoverage record);
}