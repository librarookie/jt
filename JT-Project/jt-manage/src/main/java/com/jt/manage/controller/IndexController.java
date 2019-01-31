package com.jt.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	/**
	 * 跳转页面，返回值必须是string类型
	 * 如果返回 json 返回值一般是对象 ，+@ResponseBody
	 * @return
	 */
	@RequestMapping("/index")
	public String index() {
		return "index";	//返回页面逻辑名称
	}
	
	/**
	 * 动态的从url中获取路径，则获取跳转页面名称
	 * 实现：REST结构REST-FUl
	 * 要求：
	 * 	1.发送方 参数必须凭借在url中，并且以"/"分割
	 * 	2.接收方url中的从参数必须是{}包裹
	 * 	3.为了转化参数
	 * 		- 在方法中添加一个名称一致的参数
	 * 		- 同时添加转化的注解 @PathVariable
	 * @param moduleName
	 * @return
	 */
	@RequestMapping("/page/{moduleName}")
	public String add(@PathVariable String moduleName) {
		return moduleName;
	}
}
