package com.ccic.salesapp.noncar.repository.basedb.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ccic.salesapp.noncar.repository.basedb.po.BussLog;

@Mapper
public interface BussLogMapper {
    int insert(BussLog record);

    int insertSelective(BussLog record);
}