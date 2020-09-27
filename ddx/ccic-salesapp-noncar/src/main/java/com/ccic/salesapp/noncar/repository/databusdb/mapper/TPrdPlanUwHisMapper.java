package com.ccic.salesapp.noncar.repository.databusdb.mapper;
import org.apache.ibatis.annotations.Mapper;
import com.ccic.salesapp.noncar.repository.databusdb.po.TPrdPlanUwHis;


@Mapper
public interface TPrdPlanUwHisMapper {
	
    int deleteByPrimaryKey(Long id);

    int insert(TPrdPlanUwHis record);

    int insertSelective(TPrdPlanUwHis record);

    TPrdPlanUwHis selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TPrdPlanUwHis record);

    int updateByPrimaryKey(TPrdPlanUwHis record);
}