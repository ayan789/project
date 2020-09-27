package com.ccic.salesapp.noncar.repository.databusdb.mapper;
import org.apache.ibatis.annotations.Mapper;

import com.ccic.salesapp.noncar.dto.request.noncar.PolicyRiskOrderCar;
import com.ccic.salesapp.noncar.repository.databusdb.po.TPrdPlanCt;
import com.ccic.salesapp.noncar.repository.databusdb.po.TPrdPlanCtDetail;


@Mapper
public interface TPrdPlanCtDetailMapper {
	
    int deleteByPrimaryKey(Long id);

    int insert(TPrdPlanCtDetail record);

    int insertSelective(TPrdPlanCtDetail record);

    TPrdPlanCtDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TPrdPlanCtDetail record);

    int updateByPrimaryKey(TPrdPlanCtDetail record);

	int insertTPrdPlanCtDetail(PolicyRiskOrderCar planCoverageLstCt);

}