package com.example.demo;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface SysRoleDao {
    List<Map> getByMap(Map<String, Object> map);


    @Cacheable(cacheNames="sysRoles",key="'RoleList:' + #p0")
    List<SysRole>  getByRoleList(String id,@Param("map") Map<String, Object> map);
}
