package com.jt.web.service;

import com.jt.common.po.User;

/**
 * @Author chao
 * @Date 2019/2/21 - 14:37
 */
public interface UserService {
    void saveUser(User user);

    String findUserByUP(User user);
}
