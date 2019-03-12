package com.jt.manage.service;

import com.jt.common.po.Item;
import com.jt.common.po.ItemDesc;
import com.jt.manage.vo.EasyUI_Data;

public interface ItemService {

	EasyUI_Data findItemByPage(Integer page, Integer rows);

	String findCatNameById(Long id);

	void saveItem(Item item, String desc);

	void updateItem(Item item);

	void deleteItem(Long[] ids);

	void updateStatus(Long[] ids, int status);

	ItemDesc findItemDescById(Long itemId);

    Item findItemById(Long itemId);
}
