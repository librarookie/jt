package com.jt.cart.service;

import com.jt.cart.pojo.Cart;

import java.util.List;

/**
 * @Author chao
 * @Date 2019/2/22 - 10:35
 */
public interface CartService {
    List<Cart> findCartListByUserId(Long userId);

    void updateCartNum(Cart cart);

    void saveCart(Cart cart);
}
