package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SysRoleService {
    @Autowired
    public SysRoleDao sysRoleDao;

    public List<Map> getByMap(Map<String,Object> map){
        return sysRoleDao.getByMap(map);
    }

    @CacheEvict(cacheNames="sysRoles",key="'RoleList:' + #p0",allEntries=false,beforeInvocation=true)
    public void updateRole(String id,Map<String,Object> map){
         sysRoleDao.updateRole(id,map);
    }

}

