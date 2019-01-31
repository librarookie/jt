package com.jt.manage.controller;

import com.jt.manage.service.ItemCatService;
import com.jt.manage.vo.EasyUI_Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/item")
public class ItemCatController {

	@Autowired
	private ItemCatService itemCatService;
	
	/**
	 * 实现商品树形结构展现
	 * 当页面中没有传递id  */
	@RequestMapping("/cat/list")
	@ResponseBody
	public List<EasyUI_Tree> findTree(
	@RequestParam(value="id",defaultValue="0")Long parentId) {
		return itemCatService.findTree(parentId);
	}

}
