package com.ccic.salesapp.noncar.repository.basedb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ccic.salesapp.noncar.repository.basedb.po.User;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(String usercode);

    int insert(User record);

    User selectByPrimaryKey(String usercode);

    List<User> selectAll();

    int updateByPrimaryKey(User record);
}