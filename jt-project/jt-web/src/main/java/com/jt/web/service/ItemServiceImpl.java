package com.jt.web.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.po.Item;
import com.jt.common.po.ItemDesc;
import com.jt.common.service.HttpClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chao
 * @date 2019/2/19 - 10:30
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private HttpClientService httpClientService;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Item findItemById(Long itemId) {
        // 1.定义远程访问后台的url 地址
        String url = "http://manage.jt.com/web/item/findItemById";
        // 2.定义参数
        Map<String, String> params = new HashMap<>();
        params.put("itemId", itemId + "");
        // 3.发起请求, 获取返回值结果
        String result = httpClientService.doGet(url, params);
        System.out.println("result = " + result);
        Item item = null;
        try {
            // 对象转化json, 调用对象get方法
            item = objectMapper.readValue(result, Item.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public ItemDesc findItemDescById(Long itemId) {
        // 1.定义远程访问后台的url 地址
        String url = "http://manage.jt.com/web/item/findItemDescById";
        // 2.定义参数
        Map<String, String> params = new HashMap<>();
        params.put("itemId", itemId + "");
        // 3.发起请求, 获取返回值结果
        String result = httpClientService.doGet(url, params);
        ItemDesc itemDesc = null;
        try {
            // 对象转化json, 调用对象get方法
            itemDesc = objectMapper.readValue(result, ItemDesc.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return itemDesc;
    }
}
