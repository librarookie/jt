package com.jt.manage.controller.web;

import com.jt.common.po.Item;
import com.jt.manage.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chao
 * @date 2019/2/19 - 10:52
 */
@RestController // 类中的方法返回值必须为json
@RequestMapping("/web/item")
public class WebItemController {

    @Autowired
    private ItemService itemService;

    //http://manage.jt.com/web/item/findItemById
    //返回json,因为@RestController省略@ResponseBody
    @RequestMapping("/findItemById")
    public Item findItemById(Long itemId) {
        return itemService.findItemById(itemId);
    }
}
