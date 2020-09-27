package com.ccic.salesapp.noncar.repository.databusdb.mapper;
import org.apache.ibatis.annotations.Mapper;
import com.ccic.salesapp.noncar.repository.databusdb.po.TPrdPlanOrgChg;


@Mapper
public interface TPrdPlanOrgChgMapper {
	
    int deleteByPrimaryKey(Long id);

    int insert(TPrdPlanOrgChg record);

    int insertSelective(TPrdPlanOrgChg record);

    TPrdPlanOrgChg selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TPrdPlanOrgChg record);

    int updateByPrimaryKey(TPrdPlanOrgChg record);
}