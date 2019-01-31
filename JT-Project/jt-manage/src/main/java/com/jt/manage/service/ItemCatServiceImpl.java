package com.jt.manage.service;

import com.jt.common.po.ItemCat;
import com.jt.manage.mapper.ItemCatMapper;
import com.jt.manage.vo.EasyUI_Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private ItemCatMapper itemCatMapper;
	
	public List<ItemCat> findItemCatByParentId(Long parentId) {
		ItemCat record = new ItemCat();
		record.setParentId(parentId);
		return itemCatMapper.select(record);
	}

	@Override
	public List<EasyUI_Tree> findTree(Long parentId) {
		List<ItemCat> cats = findItemCatByParentId(parentId);
		ArrayList<EasyUI_Tree> trees = new ArrayList<>();
		for (ItemCat itemCat : cats) {
			EasyUI_Tree tree = new EasyUI_Tree();
			tree.setId(itemCat.getId());
			tree.setText(itemCat.getName());
			String state = itemCat.getIsParent() ? "closed":"open";
			tree.setState(state);
			trees.add(tree);
		}
		return trees;
	}
}
