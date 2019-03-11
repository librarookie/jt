package com.jt.order.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.order.mapper.OrderItemMapper;
import com.jt.order.mapper.OrderMapper;
import com.jt.order.mapper.OrderShippingMapper;
import com.jt.order.pojo.Order;
import com.jt.order.pojo.OrderItem;
import com.jt.order.pojo.OrderShipping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Author chao
 * @Date 2019/2/25 - 15:25
 */
@Service

public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private OrderShippingMapper orderShippingMapper;

    @Override
    @Transactional // 更新操作必须加事务控制
    public String saveOrder(Order order) {
        // 拼串是注意, 运算法则
        // 入库订单表基本信息
        String orderId = "" + order.getOrderId() + System.currentTimeMillis();
        Date date = new Date();
        order.setCreated(date);
        order.setUpdated(date);
        order.setStatus(1);
        order.setOrderId(orderId);
        orderMapper.insert(order);
        System.out.println("订单表入库成功 ! !");

        // 入库订单物流信息
        OrderShipping orderShipping = order.getOrderShipping();
        orderShipping.setCreated(date);
        orderShipping.setUpdated(date);
        orderShipping.setOrderId(orderId);
        orderShippingMapper.insert(orderShipping);
        System.out.println("订单物流入库成功 ! !");

        // 入库订单商品
        List<OrderItem> orderItems = order.getOrderItems();
        for (OrderItem orderItem : orderItems) {
            orderItem.setCreated(date);
            orderItem.setUpdated(date);
            orderItem.setOrderId(orderId);
            orderItemMapper.insert(orderItem);
        }
        System.out.println("订单商品入库成功 ! !");

        return orderId;
    }

    // 根据orderId查询订单的全部信息 3张表
    @Override
    public Order findOrderById(String orderId) {
        Order order = orderMapper.selectById(orderId);
        OrderShipping orderShipping = orderShippingMapper.selectById(orderId);
        List<OrderItem> orderItems =
                orderItemMapper.selectList(new QueryWrapper<OrderItem>()
                .eq("order_id", orderId));
        // 数据库封装数据
        order.setOrderShipping(orderShipping);
        order.setOrderItems(orderItems);
        return order;
    }

    @Override
    public void updateOrderStatus() {

    }
}
