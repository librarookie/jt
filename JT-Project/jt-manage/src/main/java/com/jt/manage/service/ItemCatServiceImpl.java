package com.jt.manage.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.po.ItemCat;
import com.jt.manage.mapper.ItemCatMapper;
import com.jt.manage.vo.EasyUI_Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.clients.jedis.ShardedJedis;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private ItemCatMapper itemCatMapper;
	@Autowired
	//private Jedis jedis;
	private ShardedJedis jedis;
	@Autowired
	private  ObjectMapper objectMapper;
	
	public List<ItemCat> findItemCatByParentId(Long parentId) {
		ItemCat record = new ItemCat();
		record.setParentId(parentId);
		return itemCatMapper.select(record);
	}

	//@Override
	public List<EasyUI_Tree> findTree(Long parentId) {
		List<ItemCat> cats = findItemCatByParentId(parentId);
		ArrayList<EasyUI_Tree> trees = new ArrayList<>();
		for (ItemCat itemCat : cats) {
			EasyUI_Tree tree = new EasyUI_Tree();
			tree.setId(itemCat.getId());
			tree.setText(itemCat.getName());
			String state = itemCat.getIsParent() ? "closed" : "open";
			tree.setState(state);
			trees.add(tree);
		}
		return trees;
	}


	/**
	 * 1. 用户查询 首先查redis
	 * 2. 如果redis中没有数据, 则查询数据库, 之后将数据写入redis
	 * 3. 如果redis中有数据, 则查询缓存数据, 之后返回
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<EasyUI_Tree> findTreeCache(Long parentId) {
		String key = "ITEM_CAT_" + parentId;
		String json = jedis.get(key);
		List<EasyUI_Tree> trees = new ArrayList<>();
		try {
			if (StringUtils.isEmpty(json)) {
				trees = findTree(parentId);
				String listJSON = objectMapper.writeValueAsString(trees);
				jedis.set(key, listJSON);
				System.out.println("访问数据库 ~");
			} else {
				trees = objectMapper.readValue(json, trees.getClass());
				System.out.println("访问缓存 ~");
			}
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
		return trees;
	}
}
