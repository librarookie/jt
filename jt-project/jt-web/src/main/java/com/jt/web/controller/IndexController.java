package com.jt.web.controller;

import com.jt.common.po.Cart;
import com.jt.common.po.Order;
import com.jt.common.util.UserThreadLocalUtil;
import com.jt.web.service.CartService;
import com.jt.web.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import redis.clients.jedis.JedisCluster;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author chao
 * @date 2019/2/18 - 15:32
 */
@Controller
public class IndexController {


    @Autowired
    private JedisCluster jedisCluster;
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderService orderService;

    /**
     * web 主页跳转
     */
    @RequestMapping("/index")
    public String index() {
        return "index";
    }


    /**
     * orderController 的页面跳转
     * @param model
     * @return
     */
    @RequestMapping("/order/create")
    public String create(Model model) {
        // 准备用户记录
        Long userId = UserThreadLocalUtil.get().getId();
        // 根据userId 获取购物车记录
        List<Cart> cartList = cartService.findCartListByUserId(userId);
        model.addAttribute("carts", cartList);
        return "order-cart";
    }

    @RequestMapping("/order/success")
    public String success(String id, Model model) {
        Order order = orderService.findOrderById(id);
        model.addAttribute("order", order);
        return "success";
    }




    /**
     * CartController 的页面跳转
     * 实现购物车列表展现
     * @return
     */
    @RequestMapping("/cart/show")
    public String findCartList(Model model, HttpServletRequest request) {
        //Long userId = 7L;
        /*Long userId = (Long) request
                .getSession().getAttribute("JT_WEB_USER");*/
        Long userId = UserThreadLocalUtil.get().getId();
        List<Cart> carts = cartService.findCartListByUserId(userId);
        model.addAttribute("cartList", carts);
        return "cart";
    }
    @RequestMapping("/cart/add/{itemId}")
    public String saveCart(Cart cart) {
        //Long userId = 7L;
        Long userId = UserThreadLocalUtil.get().getId();
        cart.setUserId(userId);
        cartService.saveCart(cart);
        return "redirect:/cart/show.html";
    }





    /**
     * UserController 的页面跳转
     * @param modeName
     * @return
     */
    //www.jt.com/user/register/login.html 实现用户页面通用跳转
    @RequestMapping("/user/{modeName}")
    public String index(@PathVariable String modeName) {
        return modeName;
    }

    @RequestMapping("/user/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        // 获取cookie
        Cookie[] cookies = request.getCookies();
        String token = null;
        for (Cookie cookie : cookies) {
            if ("JT_TICKET".equals(cookie.getName())) {
                // 获取token
                token = cookie.getValue();
                break;
            }
        }
        // 先删除 redis, 再删除cookie6
        jedisCluster.del(token);
        Cookie cookie = new Cookie("JT_TICKET","");
        cookie.setMaxAge(0);    // cookie存活时间为0 表示立即删除
        cookie.setPath("/");    // 设置cookie权限,否则不生效
        // 重定向到主页 redirect(重定向) forward(转发)
        return "redirect:index.html";
    }
}
