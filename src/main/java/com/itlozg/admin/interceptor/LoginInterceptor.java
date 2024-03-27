package com.itlozg.admin.interceptor;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itlozg.admin.entity.dto.LoginDto;
import com.itlozg.admin.util.ThreadLocalUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class LoginInterceptor implements HandlerInterceptor {


    @Resource
    private RedisTemplate redisTemplate;

    @Autowired
    private static ObjectMapper objectMapper = new ObjectMapper();

    //在执行COntroller方法之前执行

    /**
     * boolean 表示是否放行
     * true:放行 用户可以跳转页面
     * false:拦截  之后给定重定向路径
     * <p>
     * 业务逻辑:
     * 1.判断用户客户端是否有Cookie/token数据
     * 如果用户没有token则重定向到用户登陆页面
     * 2.如果用户token中有数据,则从redis缓存中获取数据
     * 如果redis中数据为null,则重定向到用户登陆页面
     * 3.如果reids中有数据,则放行请求.
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        return true;
        String token = "";
        //获取Cookie数据
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("tokenUser".equals(cookie.getName())) {
                token = cookie.getValue();
                break;
            }
        }
        //判断Cookie是否为null
        if (!StringUtils.isEmpty(token)) {
            System.out.println(token);
            //检测缓存中是否有该数据
            String userinfo = (String) redisTemplate.opsForValue().get(token);

            System.out.println(userinfo);

            if (!StringUtils.isEmpty(userinfo)) {

                //将userJSON转化为User对象
                LoginDto user = JSON.parseObject(userinfo, LoginDto.class);

                ThreadLocalUtil.set(user);
//                用户已经登陆 放行请求
                return true;
            }
        }
        //表示用户没有登陆
//        response.sendRedirect("/login.html");
        return false;
    }

    //执行完业务逻辑后拦截
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub

    }

    //返回页面之前拦截
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        //将ThreadLocal数据清空
        ThreadLocalUtil.remove();
    }


}
