package com.jt.web.interceptor;

import com.jt.common.po.User;
import com.jt.common.util.MapperUtil;
import com.jt.common.util.UserThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.JedisCluster;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author chao
 * @Date 2019/2/25 - 9:45
 */
public class UserInterceptor implements HandlerInterceptor {

    @Autowired
    private JedisCluster jedisCluster;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1. 获取cookie
        Cookie[] cookies = request.getCookies();
        String token = null;
        for (Cookie cookie : cookies) {
            if ("JT_TICKET".equals(cookie.getName())) {
                token = cookie.getValue();
                break;
            }
        }
        // 2. 判断是否有记录
        if (! StringUtils.isEmpty(token)) {
            // 判断redis中是否有记录
            String userJSON = jedisCluster.get(token);
            if (! StringUtils.isEmpty(userJSON)) {
                User user = MapperUtil.toObject(userJSON, User.class);
                // 第一种方法(存到session/request域中)
                Long userId = user.getId();
                request.getSession().setAttribute("JT_WEB_USER", userId);
                // 第二种方法(存到线程中)
                UserThreadLocalUtil.set(user);
                return true;
            }
        }
        // 证明用户没有登录
        response.sendRedirect("/user/login.html");
        return false;   //true 请求放行, false 请求拦截(必须实现重定向)
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 删除session数据
        request.getSession().removeAttribute("JT_WEB_USER");
        UserThreadLocalUtil.remove();
    }
}
