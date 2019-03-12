package com.jt.web.controller;

import com.jt.common.po.User;
import com.jt.common.vo.SysResult;
import com.jt.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author chao
 * @Date 2019/2/21 - 15:28
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * SpringMVC,可以使用对象接收参数.
     * 1.password
     * @param user
     * @return
     * 这是前台Controller
     */
    @RequestMapping("/doRegister")
    //@ResponseBody   //http协议规定传输串
    public SysResult doRegister(User user) {
        try {
            userService.saveUser(user);
            return SysResult.oK();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SysResult.build(201, "用户注册失败~");
    }

    // 实现京淘用户登陆
    @RequestMapping("/doLogin")
    //@ResponseBody
    public SysResult doLogin(User user, HttpServletResponse response) {
        try {
            // 调用业务层方法,返回token数据
            String token = userService.findUserByUP(user);
            // 判断token是否为 null,非则保存到cookie
            if (! StringUtils.isEmpty(token)) {
                Cookie cookie = new Cookie("JT_TICKET", token);
                cookie.setMaxAge(24 *3600 *7);
                cookie.setPath("/");    // 设置访问权限
                response.addCookie(cookie); //添加到cookie中
                return SysResult.oK();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SysResult.build(201, "用户登录失败~");
    }
}
