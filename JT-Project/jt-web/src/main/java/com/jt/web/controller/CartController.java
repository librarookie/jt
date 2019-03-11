package com.jt.web.controller;

import com.jt.common.po.Cart;
import com.jt.common.vo.SysResult;
import com.jt.web.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author chao
 * @Date 2019/2/22 - 10:56
 */
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;


    @RequestMapping("/update/num/{itemId}/{num}")
    public SysResult upDateCartNum(
            @PathVariable Long itemId, @PathVariable Integer num) {
        try {
            Cart cart = new Cart();
            cart.setUserId(7L);
            cart.setItemId(itemId);
            cart.setNum(num);
            cartService.updateCart(cart);
            return SysResult.oK();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SysResult.build(201, "用户修改数量失败");
    }


}
