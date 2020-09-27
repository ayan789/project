package com.ccic.salesapp.noncar.repository.databusdb.mapper;
import org.apache.ibatis.annotations.Mapper;
import com.ccic.salesapp.noncar.repository.databusdb.po.TPrdPlanCtPeriod;


@Mapper
public interface TPrdPlanCtPeriodMapper {
	
    int deleteByPrimaryKey(Long id);

    int insert(TPrdPlanCtPeriod record);

    int insertSelective(TPrdPlanCtPeriod record);

    TPrdPlanCtPeriod selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TPrdPlanCtPeriod record);

    int updateByPrimaryKey(TPrdPlanCtPeriod record);
}