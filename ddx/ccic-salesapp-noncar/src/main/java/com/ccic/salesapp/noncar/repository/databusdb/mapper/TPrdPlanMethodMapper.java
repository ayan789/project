package com.ccic.salesapp.noncar.repository.databusdb.mapper;
import org.apache.ibatis.annotations.Mapper;
import com.ccic.salesapp.noncar.repository.databusdb.po.TPrdPlanMethod;


@Mapper
public interface TPrdPlanMethodMapper {
	
    int deleteByPrimaryKey(Long id);

    int insert(TPrdPlanMethod record);

    int insertSelective(TPrdPlanMethod record);

    TPrdPlanMethod selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TPrdPlanMethod record);

    int updateByPrimaryKey(TPrdPlanMethod record);

	int deleteByPlanMethodId(Long delPlanDefId);
}