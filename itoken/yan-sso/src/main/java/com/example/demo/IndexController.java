package com.example.demo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/home")
    public String index() {
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

    @GetMapping("/add")
    public String add() {
        return "新增页面";
    }

    @GetMapping("/update")
    public String update() {
        return "修改页面";
    }

    @GetMapping("/toLogin")
    public String toLogin() {
        return "请登录";
    }

    @GetMapping("/noAuth")
    public String noAuth() {
        return "权限不足";
    }

}
