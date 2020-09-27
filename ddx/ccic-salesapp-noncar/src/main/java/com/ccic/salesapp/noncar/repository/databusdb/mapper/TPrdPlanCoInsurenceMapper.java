package com.ccic.salesapp.noncar.repository.databusdb.mapper;
import org.apache.ibatis.annotations.Mapper;
import com.ccic.salesapp.noncar.repository.databusdb.po.TPrdPlanCoInsurence;

@Mapper
public interface TPrdPlanCoInsurenceMapper {
	
    int deleteByPrimaryKey(Long id);

    int insert(TPrdPlanCoInsurence record);

    int insertSelective(TPrdPlanCoInsurence record);

    TPrdPlanCoInsurence selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TPrdPlanCoInsurence record);

    int updateByPrimaryKey(TPrdPlanCoInsurence record);
}