package com.jt.sso.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jt.sso.pojo.User;
import com.jt.sso.service.UserService;
import com.jt.sso.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisCluster;

/**
 * @author chao
 * @date 2019/2/21 - 11:52
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JedisCluster jedisCluster;


    // 用户名数据回显
    @RequestMapping("/query/{token}")
    public JSONPObject findUserByToken(
            @PathVariable String token, String callback) {
        String userJSON = jedisCluster.get(token);
        System.out.println("userJSON = " + userJSON);
        SysResult sysResult = SysResult.oK(userJSON);
        return new JSONPObject(callback, sysResult);
    }

    //http:/sso.jt.com/user/register
    @RequestMapping("/register")
    public SysResult saveUser(User user) {
        try {
            userService.saveUser(user);
            return SysResult.oK();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SysResult.build(201, "用户新增失败 !");
    }
    //http:/sso.jt.com/user/login
    @RequestMapping("/login")
    public SysResult findUserByUP(User user) {
        try {
            String token = userService.findUserByUP(user);
            if (! StringUtils.isEmpty(token)) {
                return SysResult.oK(token);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SysResult.build(201, "用户登录失败 !");
    }


    //http://sso.jt.com/user/check/dsafgreqwtqdfs/1?r=0.88&callback=jsonp1507803
    @RequestMapping("/check/{param}/{type}")
    public JSONPObject findCheckUser(@PathVariable String param,
                                     @PathVariable Integer type, String callback) {
        // 获取数据的返回数据
        Boolean flag = userService.findCheckUser(param,type);
        // 封装返回数据
        SysResult result = SysResult.oK(flag);
        return new JSONPObject(callback, result);
    }

}
