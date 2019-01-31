package com.jt.manage.service;

import java.util.List;

import com.jt.manage.vo.EasyUI_Tree;

public interface ItemCatService {

	List<EasyUI_Tree> findTree(Long parentId);

}
