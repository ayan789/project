package com.example.demo;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface UserInfoDao {

    List<Map> getByMap(Map<String, Object> map);

    UserInfo getByUid(Map<String, Object> map);

    UserInfo getByUserName(Map<String, Object> map);

}

