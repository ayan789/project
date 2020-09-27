package com.ccic.salesapp.noncar.repository.databusdb.mapper;
import org.apache.ibatis.annotations.Mapper;
import com.ccic.salesapp.noncar.repository.databusdb.po.TPrdPlanChg;


@Mapper
public interface TPrdPlanChgMapper {
	
    int deleteByPrimaryKey(Long id);

    int insert(TPrdPlanChg record);

    int insertSelective(TPrdPlanChg record);

    TPrdPlanChg selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TPrdPlanChg record);

    int updateByPrimaryKey(TPrdPlanChg record);
}