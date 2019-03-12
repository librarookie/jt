package com.jt.order.controller;

import com.jt.order.pojo.Order;
import com.jt.order.service.OrderService;
import com.jt.order.util.MapperUtil;
import com.jt.order.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author chao
 * @Date 2019/2/25 - 15:22
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/create")
    public SysResult saveOrder(String orderJSON) {
        try {
            Order order = MapperUtil.toObject(orderJSON, Order.class);
            String orderId = orderService.saveOrder(order);
            if (! StringUtils.isEmpty(orderId)) {
                return SysResult.oK(orderId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SysResult.build(201, "订单入库失败 ! !");
    }

    @RequestMapping("/findOrderById/{orderId}")
    public Order findOrderById(@PathVariable String orderId) {
        return orderService.findOrderById(orderId);
    }
}
