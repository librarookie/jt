package com.jt.web.controller;

import com.jt.common.po.Order;
import com.jt.common.util.UserThreadLocalUtil;
import com.jt.common.vo.SysResult;
import com.jt.web.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author chao
 * @Date 2019/2/25 - 14:37
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // 实现订单新增
    @RequestMapping("/submit")
    public SysResult saveOrder(Order order) {
        try {
            Long userId = UserThreadLocalUtil.get().getId();
            order.setUserId(userId);
            String orderId =  orderService.saveOrder(order);
            if (! StringUtils.isEmpty(orderId)) {
                return SysResult.oK(orderId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SysResult.build(201, "订单提交失败 ! !");
    }

}
