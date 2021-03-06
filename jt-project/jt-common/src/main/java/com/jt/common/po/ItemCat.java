package com.jt.common.po;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author chao
 * @date 2019/1/25 - 15:35
 */
@Table(name="tb_item_cat")
public class ItemCat extends BasePojo {

	@Id		//定义注解, 主键自增
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;		//主键
	private Long parentId;	//父级ID(外键)
	private String name;	//分类名称
	private Integer status;	//状态
	private Integer sortOrder;	//排序号
	private Boolean isParent;	//是否为父级true/closed, false/open
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}
	public Boolean getIsParent() {
		return isParent;
	}
	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}
}
