package com.ccic.salesapp.noncar.repository.databusdb.mapper;
import org.apache.ibatis.annotations.Mapper;
import com.ccic.salesapp.noncar.repository.databusdb.po.TPrdPlanOrg;


@Mapper
public interface TPrdPlanOrgMapper {
	
    int deleteByPrimaryKey(Long id);

    int insert(TPrdPlanOrg record);

    int insertSelective(TPrdPlanOrg record);

    TPrdPlanOrg selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TPrdPlanOrg record);

    int updateByPrimaryKey(TPrdPlanOrg record);

	int deleteByPlanOrgId(Long delPlanDefId);
}