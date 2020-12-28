package com.ccic.salesapp.noncar.repository.basedb.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ccic.salesapp.noncar.repository.basedb.po.MarketingInteration;

@Mapper
public interface MarketingInterationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MarketingInteration record);

    int insertSelective(MarketingInteration record);

    MarketingInteration selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MarketingInteration record);

    int updateByPrimaryKey(MarketingInteration record);
}