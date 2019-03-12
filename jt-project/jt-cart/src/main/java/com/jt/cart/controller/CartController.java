package com.jt.cart.controller;

import com.jt.cart.pojo.Cart;
import com.jt.cart.service.CartService;
import com.jt.cart.util.MapperUtil;
import com.jt.cart.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author chao
 * @Date 2019/2/22 - 10:32
 */
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    //http://cart.jt.com/query/
    @RequestMapping("/query/{userId}")
    public SysResult findCartListByUserId(@PathVariable Long userId) {
        List<Cart> carts = cartService.findCartListByUserId(userId);
        return SysResult.oK(carts);
    }

    @RequestMapping("/update/num/{userId}/{itemId}/{num}")
    public SysResult updateCartNum(Cart cart) {
        try {
            cartService.updateCartNum(cart);
            return SysResult.oK();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SysResult.build(201, "购物车修改失败");
    }

    @RequestMapping("/save")
    public SysResult saveCart(String cartJSON) {
        try {
            Cart cart = MapperUtil.toObject(cartJSON, Cart.class);
            cartService.saveCart(cart);
            return SysResult.oK();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SysResult.build(201, "加入购入车失败");
    }

}
