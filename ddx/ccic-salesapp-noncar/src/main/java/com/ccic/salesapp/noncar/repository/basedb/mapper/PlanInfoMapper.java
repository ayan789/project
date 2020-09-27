package com.ccic.salesapp.noncar.repository.basedb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ccic.salesapp.noncar.dto.order.request.Element;
import com.ccic.salesapp.noncar.repository.basedb.po.PlanInfo;
@Mapper
public interface PlanInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PlanInfo record);

    int insertSelective(PlanInfo record);

    PlanInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PlanInfo record);

    int updateByPrimaryKey(PlanInfo record);
    
    List<Element> selectComputeElementsByPlanId(Integer id);
}