package com.jt.web.service;

import com.jt.common.po.Item;
import com.jt.common.po.ItemDesc;

/**
 * @author chao
 * @date 2019/2/19 - 10:20
 */
public interface ItemService {

    Item findItemById(Long itemId);

    ItemDesc findItemDescById(Long itemId);
}
