package com.example.demo;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface SysRoleDao {
    List<Map> getByMap(Map<String, Object> map);

    List<SysRole>  getByRoleList(Map<String, Object> map);
}
