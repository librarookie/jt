package com.jt.manage.controller;

import com.jt.manage.pojo.User;
import com.jt.manage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/doFindAll2")
	public ModelAndView doFindAll2() {
		List<User> users = userService.findAll();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("userList");
		mv.addObject("users", users);
		return mv;
	}
	
	//实现测试数据查询
	@RequestMapping("/doFindAll")
	public String doFindAll(Model model) {
		List<User> users = userService.findAll();
		//默认使用request域
		model.addAttribute("list", users);
		return "userList";//返回页面逻辑名称（jsp/html）
	}
}
