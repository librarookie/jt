package com.jt.order.service;

import com.jt.order.pojo.Order;

/**
 * @Author chao
 * @Date 2019/2/25 - 15:26
 */
public interface OrderService {
    String saveOrder(Order order);

    Order findOrderById(String orderId);

    void updateOrderStatus();
}
