package com.jt.web.service;

import com.jt.common.po.Order;
import com.jt.common.service.HttpClientService;
import com.jt.common.util.MapperUtil;
import com.jt.common.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author chao
 * @Date 2019/2/25 - 16:15
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private HttpClientService httpClientService;


    @Override
    public String saveOrder(Order order) {
        String url = "http://order.jt.com/order/create";
        String orderJSON = MapperUtil.toJSON(order);
        Map<String, String> params = new HashMap<>();
        params.put("orderJSON", orderJSON);
        String result = httpClientService.doPost(url, params);
        SysResult sysResult = MapperUtil.toObject(result, SysResult.class);
        String orderId = null;
        if (sysResult.getStatus() == 200) {
            orderId = (String) sysResult.getData();
        }
        return orderId;
    }

    @Override
    public Order findOrderById(String id) {
        String url = "http://order.jt.com/order/findOrderById/" + id;
        String orderJSON = httpClientService.doGet(url);
        Order order = MapperUtil.toObject(orderJSON, Order.class);
        return order;
    }
}
