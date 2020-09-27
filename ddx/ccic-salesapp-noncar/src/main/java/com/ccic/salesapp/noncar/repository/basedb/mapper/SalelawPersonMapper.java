package com.ccic.salesapp.noncar.repository.basedb.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ccic.salesapp.noncar.repository.basedb.po.SalelawPerson;

@Mapper
public interface SalelawPersonMapper {
    int deleteByPrimaryKey(String usercode);

    int insert(SalelawPerson record);

    int insertSelective(SalelawPerson record);

    SalelawPerson selectByPrimaryKey(String usercode);

    int updateByPrimaryKeySelective(SalelawPerson record);

    int updateByPrimaryKey(SalelawPerson record);
}