package com.ccic.salesapp.noncar.repository.databusdb.mapper;
import org.apache.ibatis.annotations.Mapper;
import com.ccic.salesapp.noncar.repository.databusdb.po.TPrdPlanLimit;


@Mapper
public interface TPrdPlanLimitMapper {
	
    int deleteByPrimaryKey(Long id);

    int insert(TPrdPlanLimit record);

    int insertSelective(TPrdPlanLimit record);

    TPrdPlanLimit selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TPrdPlanLimit record);

    int updateByPrimaryKey(TPrdPlanLimit record);

	int deletePlanLimitById(Long planCoverageId);
}