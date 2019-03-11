package com.jt.web.service;

import com.jt.common.po.Order;

/**
 * @Author chao
 * @Date 2019/2/25 - 16:11
 */
public interface OrderService {

    String saveOrder(Order order);

    Order findOrderById(String id);
}
