package com.jt.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author chao
 * @date 2019/1/29 - 20:37
 */
@Controller
public class helleController {

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello ~";
    }
}
