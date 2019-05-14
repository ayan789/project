package com.example.demo;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceTest extends BaseTest {


    @Autowired
    private UserService userService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private SysRoleService sysRoleService;

    @Test
    public void dynamicDataSourceTest() throws Exception {
        List<User> userList = userService.getByMap(null);
        System.out.println("--userList---" + userList.toString());

    }

    @Test
    public void userInfoTest() throws Exception {
        List<Map> userList = userInfoService.getByMap(null);
        System.out.println("--userList---" + userList.toString());

    }

    @Test
    public void sysRoleTest() throws Exception {
        List<Map> sysROleList = sysRoleService.getByMap(null);
        System.out.println("--userList---" + sysROleList.toString());
    }

    @Test
    public void userDtoTest() throws Exception {
        Map<String,Object> map = new HashMap<>();
        map.put("uid","1");
        UserInfo userInfo = userInfoService.getByUid(map);
        System.out.println("--userList---" + userInfo.toString());

       SecurityUtils.getSubject().getPreviousPrincipals();
    }


}

