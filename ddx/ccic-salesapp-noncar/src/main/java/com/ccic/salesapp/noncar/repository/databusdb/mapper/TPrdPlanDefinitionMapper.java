package com.ccic.salesapp.noncar.repository.databusdb.mapper;
import org.apache.ibatis.annotations.Mapper;
import com.ccic.salesapp.noncar.repository.databusdb.po.TPrdPlanDefinition;


@Mapper
public interface TPrdPlanDefinitionMapper {
	
    int deleteByPrimaryKey(Long id);

    int insert(TPrdPlanDefinition record);

    int insertSelective(TPrdPlanDefinition record);

    TPrdPlanDefinition selectByPrimaryKey(Long id);
    
    TPrdPlanDefinition selectByPlanDefId(Long planDefId);

    int updateByPrimaryKeySelective(TPrdPlanDefinition record);

    int updateByPrimaryKey(TPrdPlanDefinition record);

    int deleteByPlanDefinitionId(Long delPlanDefId);
}