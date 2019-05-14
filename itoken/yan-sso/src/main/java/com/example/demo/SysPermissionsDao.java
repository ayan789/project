package com.example.demo;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface SysPermissionsDao {
    List<SysPermissions>  getByPermissionsList(Map<String, Object> map);

    List<SysPermissions>  getAllPermisList();
}
