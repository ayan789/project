package com.example.demo;

import com.alibaba.fastjson.JSON;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 自定义Realm
 *
 * @author lenovo
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserInfoDao userInfoDao;

    @Autowired
    private SysRoleDao sysRoleDao;

    @Autowired
    private SysPermissionsDao sysPermissionsDao;

    /**
     * 执行授权逻辑
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
        System.out.println("执行授权逻辑");
        Subject subject = SecurityUtils.getSubject();
        UserInfo user = (UserInfo) subject.getPrincipal();
        Map<String, Object> map = new HashMap<>();
        map.put("uid", user.getUid());
        List<SysRole> roleList = sysRoleDao.getByRoleList(map);
        UserInfo userInfo = userInfoDao.getByUid(map);
        userInfo.setRoleList(roleList);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        List<String> permissions = new ArrayList<>();
        for (int i = 0; i < userInfo.getRoleList().size(); i++) {
            Map<String, Object> rolemap = new HashMap<>();
            rolemap.put("roleId", userInfo.getRoleList().get(i).getId());
            List<SysPermissions> sysPermissionsList = sysPermissionsDao.getByPermissionsList(rolemap);
            userInfo.getRoleList().get(i).setSysPermissionsList(sysPermissionsList);
            for (int j = 0; j < sysPermissionsList.size(); j++) {
                permissions.add(sysPermissionsList.get(j).getPermission());
            }
        }
        info.addStringPermissions(permissions);//将权限放入shiro中.
        String jsonStr = JSON.toJSONString(userInfo);
        System.out.println(jsonStr);
        return info;
    }

    /**
     * 执行认证逻辑
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
        System.out.println("执行认证逻辑");
        //编写shiro判断逻辑，判断用户名和密码
        //1.判断用户名
        UsernamePasswordToken token = (UsernamePasswordToken) arg0;
        Map<String, Object> map = new HashMap<>();
        map.put("uaerName", token.getUsername());
        UserInfo user = userInfoDao.getByUserName(map);
        if (user == null) {
            //用户名不存在
            return null;//shiro底层会抛出UnKnowAccountException
        }
        //4)credentialsSalt盐值
        ByteSource credentialsSalt = ByteSource.Util.bytes(ShiroUtil.createSalt());//使用账号作为盐值
        String saltPassword = ShiroUtil.salt(token.getPassword(), credentialsSalt.toString());
        //判断密码
       if (null == user.getPassword()) {
            throw new AccountException();
        } else if (!user.getPassword().equals(saltPassword)) {
            throw new AccountException();
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, token.getPassword(), getName());
        //session中不需要保存密码
        user.setPassword("");
        //将用户信息放入session中
        SecurityUtils.getSubject().getSession().setAttribute("SESSION_USER_INFO", user);
        return authenticationInfo;
    }

}
