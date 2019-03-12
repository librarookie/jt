package com.jt.web.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.po.User;
import com.jt.common.service.HttpClientService;
import com.jt.common.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author chao
 * @Date 2019/2/21 - 14:36
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private HttpClientService httpClientService;
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 发起HTTPClient post请求
     * http://sso.jt.com/user/register
     */
    @Override
    public void saveUser(User user) {
        String url = "http://sso.jt.com/user/register";
        Map<String, String> params = new HashMap<>();
        //将密码加密处理
        String md5PW = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        params.put("username", user.getUsername());
        params.put("password", md5PW);
        params.put("phone", user.getPhone());
        params.put("email", user.getEmail());
        // 发起post远程请求, 获取回执
        String result = httpClientService.doPost(url, params);
        try {
            SysResult sysResult = objectMapper.readValue(result, SysResult.class);
            //获取数据后,校验后台执行情况 200表示正确,201表示失败
            if (sysResult.getStatus() != 200) {
                System.out.println("sso后台注册错误 " + sysResult);
                throw new RuntimeException();
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    /**
     * 登录
     * http:/sso.jt.com/user/login
     */
    @Override
    public String findUserByUP(User user) {
        String url = "http://sso.jt.com/user/login";
        Map<String, String> params = new HashMap<>();
        //将密码加密处理,必须和注册的加密算法一致
        String md5PW = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        params.put("username", user.getUsername());
        params.put("password", md5PW);
        // 发起post的请求
        String result = httpClientService.doPost(url, params);
        String token = null;
        try {
            SysResult sysResult = objectMapper.readValue(result, SysResult.class);
            if (sysResult.getStatus() == 200) {
                token = (String) sysResult.getData();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return token;
    }

}
