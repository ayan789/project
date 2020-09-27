package com.ccic.salesapp.noncar.repository.databusdb.mapper;
import org.apache.ibatis.annotations.Mapper;
import com.ccic.salesapp.noncar.repository.databusdb.po.TPrdPlanInfo;


@Mapper
public interface TPrdPlanInfoMapper {
	
    int deleteByPrimaryKey(Long id);

    int insert(TPrdPlanInfo record);

    int insertSelective(TPrdPlanInfo record);

    TPrdPlanInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TPrdPlanInfo record);

    int updateByPrimaryKey(TPrdPlanInfo record);

    int deleteByPlanDefId(Long planDefId);

	int deleteByPlanCtDetialDefId(Long planDefId);

}