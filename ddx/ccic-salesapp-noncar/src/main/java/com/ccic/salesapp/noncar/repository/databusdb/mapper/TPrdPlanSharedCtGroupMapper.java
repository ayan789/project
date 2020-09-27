package com.ccic.salesapp.noncar.repository.databusdb.mapper;
import org.apache.ibatis.annotations.Mapper;
import com.ccic.salesapp.noncar.repository.databusdb.po.TPrdPlanSharedCtGroup;


@Mapper
public interface TPrdPlanSharedCtGroupMapper {
	
    int deleteByPrimaryKey(Long id);

    int insert(TPrdPlanSharedCtGroup record);

    int insertSelective(TPrdPlanSharedCtGroup record);

    TPrdPlanSharedCtGroup selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TPrdPlanSharedCtGroup record);

    int updateByPrimaryKey(TPrdPlanSharedCtGroup record);

	int deleteByPlanSharedCtGroupId(Long delPlanDefId);
}