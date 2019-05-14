package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserInfoService {
    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private SysRoleDao sysRoleDao;

    @Autowired
    private SysPermissionsDao sysPermissionsDao;

    public List<Map> getByMap(Map<String, Object> map) {
        return userInfoDao.getByMap(map);
    }

    public UserInfo getByUid(Map<String, Object> map) {
        List<SysRole> roleList = sysRoleDao.getByRoleList(map);
        UserInfo userInfo = userInfoDao.getByUid(map);
        userInfo.setRoleList(roleList);
        for(int i=0;i<userInfo.getRoleList().size();i++){
            Map<String, Object> rolemap = new HashMap<>();
            rolemap.put("roleId", userInfo.getRoleList().get(i).getId());
            List<SysPermissions> sysPermissionsList = sysPermissionsDao.getByPermissionsList(rolemap);
            userInfo.getRoleList().get(i).setSysPermissionsList(sysPermissionsList);
        }
        String jsonStr = JSON.toJSONString(userInfo);
        System.out.println(jsonStr);
        return userInfo;
    }

}

