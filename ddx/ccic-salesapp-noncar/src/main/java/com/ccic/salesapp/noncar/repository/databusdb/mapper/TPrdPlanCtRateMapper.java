package com.ccic.salesapp.noncar.repository.databusdb.mapper;
import org.apache.ibatis.annotations.Mapper;
import com.ccic.salesapp.noncar.repository.databusdb.po.TPrdPlanCtRate;


@Mapper
public interface TPrdPlanCtRateMapper {
	
    int deleteByPrimaryKey(Long id);

    int insert(TPrdPlanCtRate record);

    int insertSelective(TPrdPlanCtRate record);

    TPrdPlanCtRate selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TPrdPlanCtRate record);

    int updateByPrimaryKey(TPrdPlanCtRate record);

	int deletePlanCtRateById(Long planCoverageId);
}