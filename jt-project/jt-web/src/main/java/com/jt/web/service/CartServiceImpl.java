package com.jt.web.service;

import com.jt.common.po.Cart;
import com.jt.common.service.HttpClientService;
import com.jt.common.util.MapperUtil;
import com.jt.common.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author chao
 * @Date 2019/2/22 - 10:56
 */
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private HttpClientService httpClientService;


    @Override
    public List<Cart> findCartListByUserId(Long userId) {
        String url = "http://cart.jt.com/cart/query/" + userId;
        String result =  httpClientService.doGet(url);
        SysResult sysResult = MapperUtil.toObject(result, SysResult.class);
        System.out.println("sysResult = " + sysResult);
        List<Cart> cartList = (List<Cart>) sysResult.getData();
        return cartList;
    }


    // 实现购物车数量的修改
    @Override
    public void updateCart(Cart cart) {
        String url = "http://cart.jt.com/cart/update/num/" +cart.getUserId()
                +"/"+cart.getItemId() +"/"+cart.getNum();

        httpClientService.doGet(url);
    }

    @Override
    public void saveCart(Cart cart) {
        String url = "http://cart.jt.com/cart/save";
        String json = MapperUtil.toJSON(cart);
        Map<String, String> params = new HashMap<>();
        params.put("cartJSON", json);
        httpClientService.doPost(url, params);
    }
}
