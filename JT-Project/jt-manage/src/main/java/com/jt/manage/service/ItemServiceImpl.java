package com.jt.manage.service;

import com.jt.common.po.Item;
import com.jt.common.po.ItemDesc;
import com.jt.manage.mapper.ItemDescMapper;
import com.jt.manage.mapper.ItemMapper;
import com.jt.manage.vo.EasyUI_Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private ItemDescMapper itemDescMapper;

	@Override
	public EasyUI_Data findItemByPage(Integer page, Integer rows) {
		int total = itemMapper.findCount();	//商品记录总数
		int start = (page-1)*rows;
		List<Item> items = itemMapper.findItemByPage(start,rows);	//分页后,查询的结果
		return new EasyUI_Data(total, items);	
	}

	@Override
	public String findCatNameById(Long itemId) {
		return itemMapper.findCatNameById(itemId);
	}

	@Override
	public void saveItem(Item item,String desc) {
		item.setStatus(1);	//该商品正常
		item.setCreated(new Date());
		item.setUpdated(item.getCreated());
		//利用通用mapper实现入库操作
		itemMapper.insert(item);
		
		// 入库商品详情信息
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemDesc(desc);
		itemDesc.setItemId(item.getId());
		itemDesc.setCreated(item.getCreated());
		itemDesc.setUpdated(item.getCreated());
		itemDescMapper.insert(itemDesc);
	}

	@Override
	public void updateItem(Item item) {
		item.setUpdated(new Date());
		itemMapper.updateByPrimaryKeySelective(item);
	}

	@Override
	public void deleteItem(Long[] ids) {
		itemMapper.deleteByIDS(ids);
		itemDescMapper.deleteByIDS(ids);
	}

	@Override
	public void updateStatus(Long[] ids, int status) {
		itemMapper.updateStatus(ids,status);
	}

	@Override
	public ItemDesc findItemDescById(Long itemId) {
		return itemDescMapper.selectByPrimaryKey(itemId);
	}
}
