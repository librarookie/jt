package com.jt.manage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.po.Item;
import com.jt.common.po.ItemDesc;
import com.jt.common.vo.SysResult;
import com.jt.manage.service.ItemService;
import com.jt.manage.vo.EasyUI_Data;

@Controller
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private ItemService itemService;

	//"http://manage.jt.com/web/item/findItemById"
	@RequestMapping("/query/item/desc/{itemId}")
	@ResponseBody
	public SysResult findItemDescById(@PathVariable Long itemId) {
		try {
			ItemDesc itemDesc = itemService.findItemDescById(itemId);
			return SysResult.oK(itemDesc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201, "查询数据失败 ! ! ");
	}
	//实现商品列表的分页查询
	@RequestMapping("/query")
	@ResponseBody	//将数据转化为JSON
	public EasyUI_Data findItemByPage(Integer page,Integer rows) {
		return itemService.findItemByPage(page, rows);
	}
	/**
	 * spring4 及以
	 * @param itemId
	 * @return
	 */
	@RequestMapping(value="/cat/queryItemName",
			produces="text/html;charset=utf-8")
	@ResponseBody	//将数据转化为JSON
	public String findCatNameById(Long itemId) {
		return itemService.findCatNameById(itemId);
	}

	@RequestMapping("/save")
	@ResponseBody
	public SysResult saveItem(Item item,String desc) {
		try {
			itemService.saveItem(item,desc);
			return SysResult.oK();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201, "商品新增失败 !!");
	}
	@RequestMapping("/update")	//调用Set方法
	@ResponseBody	//调用Get方法
	public SysResult updateItem(Item item) {
		try {
			itemService.updateItem(item);
			return SysResult.oK();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201, "商品修改失败 !!");
	}
	@RequestMapping("/delete")	//调用Set方法
	@ResponseBody	//调用Get方法
	public SysResult deleteItem(Long[] ids) {
		try {
			itemService.deleteItem(ids);
			return SysResult.oK();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201, "商品删除失败 !!");
	}
	@RequestMapping("/instock")	//调用Set方法
	@ResponseBody	//调用Get方法
	public SysResult instockItem(Long[] ids) {
		try {
			int status = 2;	//下架
			itemService.updateStatus(ids,status);
			return SysResult.oK();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201, "商品下架失败 !!");
	}
	@RequestMapping("/reshelf")	//调用Set方法
	@ResponseBody	//调用Get方法
	public SysResult reshelfItem(Long[] ids) {
		try {
			int status = 1;	//上架
			itemService.updateStatus(ids,status);
			return SysResult.oK();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201, "商品上架失败 !!");
	}
}
