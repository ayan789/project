package com.ccic.salesapp.noncar.repository.databusdb.mapper;
import org.apache.ibatis.annotations.Mapper;
import com.ccic.salesapp.noncar.repository.databusdb.po.TPrdPlanRi;


@Mapper
public interface TPrdPlanRiMapper {
	
    int deleteByPrimaryKey(Long id);

    int insert(TPrdPlanRi record);

    int insertSelective(TPrdPlanRi record);

    TPrdPlanRi selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TPrdPlanRi record);

    int updateByPrimaryKey(TPrdPlanRi record);

	int deleteByPlanRiId(Long delPlanDefId);
}