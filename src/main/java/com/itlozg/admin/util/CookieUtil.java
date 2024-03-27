package com.itlozg.admin.util;

import com.alibaba.fastjson.JSON;
import com.itlozg.admin.entity.dto.LoginDto;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
@Component
public class CookieUtil {
    @Resource
    private RedisTemplate redisTemplate;

    public void token(HttpServletResponse response, LoginDto userinfo){
        String token = UUID.randomUUID().toString();
        Cookie cookie=new Cookie("tokenUser",token);
        cookie.setMaxAge(60 * 60 * 24 * 7);//有效期设为7天
        cookie.setPath("/");//设置路径
        response.addCookie(cookie);//响应回游览器
        String user = JSON.toJSONString(userinfo);
        redisTemplate.opsForValue().set(token,user, 24 * 7, TimeUnit.HOURS);//redis中存储用户信息并设置存储时间
    }

}
