package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SysRoleService {
    @Autowired
    private SysRoleDao sysRoleDao;

    public List<Map> getByMap(Map<String,Object> map){
        return sysRoleDao.getByMap(map);
    }


}

