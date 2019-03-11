package com.jt.web.controller;

import com.jt.common.po.Item;
import com.jt.common.po.ItemDesc;
import com.jt.web.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author chao
 * @date 2019/2/19 - 10:10
 */
@Controller
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    // 实现商品详情展现 http://www.jt.com/items/562379.html
    @RequestMapping("/{itemId}")
    public String findItemById(@PathVariable("itemId")Long itemId, Model model) {
        Item item = itemService.findItemById(itemId);
        ItemDesc itemDesc = itemService.findItemDescById(itemId);
        model.addAttribute("item", item);
        model.addAttribute("itemDesc", itemDesc);
        return "item";
    }

}
