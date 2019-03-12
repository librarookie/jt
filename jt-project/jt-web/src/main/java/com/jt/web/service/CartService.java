package com.jt.web.service;

import com.jt.common.po.Cart;

import java.util.List;

/**
 * @Author chao
 * @Date 2019/2/22 - 10:56
 */
public interface CartService {
    List<Cart> findCartListByUserId(Long userId);

    void updateCart(Cart cart);

    void saveCart(Cart cart);
}
