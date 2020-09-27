package com.ccic.salesapp.noncar.repository.databusdb.mapper;
import org.apache.ibatis.annotations.Mapper;

import com.ccic.salesapp.noncar.dto.request.noncar.PolicyRiskOrderCar;
import com.ccic.salesapp.noncar.repository.databusdb.po.TPrdPlanCt;
import com.ccic.salesapp.noncar.repository.databusdb.po.TPrdPlanCtChild;


@Mapper
public interface TPrdPlanCtMapper {
	
    int deleteByPrimaryKey(Long id);

    int insert(TPrdPlanCt record);
    
    int insertChild(TPrdPlanCtChild record);

    int insertSelective(TPrdPlanCt record);

    TPrdPlanCt selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TPrdPlanCt record);

    int updateByPrimaryKey(TPrdPlanCt record);

	TPrdPlanCt findParentPlanCoverageId(Long long1);

	TPrdPlanCt findParentPlanCoverageId1(Long long1);

	void insertChildPlanCoverage(PolicyRiskOrderCar planCoverageLst);
}