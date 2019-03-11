package com.jt.sso.service;

import com.jt.sso.pojo.User;

/**
 * @Author chao
 * @Date 2019/2/21 - 14:37
 */
public interface UserService {
    Boolean findCheckUser(String param, Integer type);

    void saveUser(User user);

    String findUserByUP(User user);
}
