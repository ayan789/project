package com.ccic.salesapp.noncar.repository.basedb.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ccic.salesapp.noncar.repository.basedb.po.RenewalDatabusExpiredData;

@Mapper
public interface RenewalDatabusExpiredDataMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RenewalDatabusExpiredData record);

    int insertSelective(RenewalDatabusExpiredData record);

    RenewalDatabusExpiredData selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RenewalDatabusExpiredData record);

    int updateByPrimaryKey(RenewalDatabusExpiredData record);
    
    int updateNewPolicyNoByPolicyNo(Map map);
}