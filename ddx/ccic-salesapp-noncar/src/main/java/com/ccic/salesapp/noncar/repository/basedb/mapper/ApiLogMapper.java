package com.ccic.salesapp.noncar.repository.basedb.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ccic.salesapp.noncar.repository.basedb.po.ApiLog;

@Mapper
public interface ApiLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ApiLog record);

    int insertSelective(ApiLog record);

    ApiLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ApiLog record);

    int updateByPrimaryKey(ApiLog record);
}