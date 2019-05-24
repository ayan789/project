package com.example.demo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/userInfo")
public class IndexController {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SysRoleDao sysRoleDao;

    @Autowired
    SysRoleService sysRoleService;

    @Autowired
    private RedisService redisService;

    @GetMapping("/home")
    public String index(String name, String password)  {
        System.out.println("home");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "up SSO";
    }

    @GetMapping("/login")
    public String login(String name, String password) {
        /**
         * 使用Shiro编写认证操作
         */
        //1.获取Subject
        Subject subject = SecurityUtils.getSubject();

        //2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(name, password);

        //3.执行登录方法
        try {
            subject.login(token);
        } catch (UnknownAccountException uae) {
            logger.info("对用户[" + name + "]进行登录验证..验证未通过,未知账户");
            logger.info("message", "未知账户");
            return "未知账户";
        } catch (IncorrectCredentialsException ice) {
            logger.info("对用户[" + name + "]进行登录验证..验证未通过,错误的凭证");
            logger.info("message", "密码不正确");
            return "密码不正确";
        } catch (LockedAccountException lae) {
            logger.info("对用户[" + name + "]进行登录验证..验证未通过,账户已锁定");
            logger.info("message", "账户已锁定");
            return "账户已锁定";//返回登录页面
        } catch (ExcessiveAttemptsException eae) {
            logger.info("对用户[" + name + "]进行登录验证..验证未通过,错误次数过多");
            logger.info("message", "用户名或密码错误次数过多");
            return "用户名或密码错误次数过多";//返回登录页面
        } catch (AuthenticationException ae) {
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
            logger.info("对用户[" + name + "]进行登录验证..验证未通过,堆栈轨迹如下");
//            ae.printStackTrace();
            logger.info("message", "用户名或密码不正确");
            return "用户名或密码不正确";//返回登录页面
        }
        //登录成功
        return "登录成功";
    }

    @GetMapping("/userAdd")
    public String add() {
        return "新增页面";
    }

    @GetMapping("/userUpdate")
    public String update() {
        return "修改页面";
    }

    @GetMapping("/toLogin")
    public String toLogin() {
        //1.获取Subject
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "请登录";
    }

    @GetMapping("/noAuth")
    public String noAuth() {
        return "权限不足";
    }


    @GetMapping("/redis")
    public String redisRole(String id) {
        Map<String, Object> map = new HashMap<>();
        map.put("uid", "1");
        sysRoleDao.getByRoleList(id,map);
        return "已缓存";
    }

    @GetMapping("/redisUpdate")
    public String redisUpdateRole(String id) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", "1");
        sysRoleService.updateRole(id,map);
        return "已清空缓存";
    }

    @GetMapping("/redisAdd")
    public String redisAdd() {
        redisService.set("shuai","hello redis",10L);
        return "自定义添加缓存";
    }

}
