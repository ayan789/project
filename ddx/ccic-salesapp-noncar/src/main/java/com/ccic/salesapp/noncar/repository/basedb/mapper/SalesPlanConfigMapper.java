package com.ccic.salesapp.noncar.repository.basedb.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ccic.salesapp.noncar.repository.basedb.po.SalesPlanConfig;

@Mapper
public interface SalesPlanConfigMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SalesPlanConfig record);

    int insertSelective(SalesPlanConfig record);

    SalesPlanConfig selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SalesPlanConfig record);

    int updateByPrimaryKey(SalesPlanConfig record);
}