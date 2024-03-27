package com.itlozg.admin.controller;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.itlozg.admin.entity.VEmployees;
import com.itlozg.admin.entity.dto.DDSSOLogin;
import com.itlozg.admin.entity.dto.LoginDto;
import com.itlozg.admin.mapper.VEmployeesMapper;
import com.itlozg.admin.service.IUserLoginService;
import com.itlozg.admin.service.IVEmployeesService;
import com.itlozg.admin.util.BeanCopier;
import com.itlozg.admin.util.CommonResult;
import com.itlozg.admin.util.CookieUtil;
import com.itlozg.admin.util.ThreadLocalUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@Controller
@ResponseBody
//@RequestMapping("/api/user")
@RequestMapping("/api")
@Api(tags="用户Controller")
public class UserController {
//    @RequestMapping(value = "/ssoLogin123", method = RequestMethod.POST)
//    public void  login(@RequestParam("code") String code) throws Exception {
//        String sendBy="501905df0b924bea868011f70a6f4dc3";
//        String loginForm = "{" +
//                "\"code  \":\"" + code + "\"," +
//                "\"sendBy\":\""+sendBy+  "\""+
//                "}";
//    }
    @Autowired
    private IUserLoginService userLoginService;

    @Autowired
    private CookieUtil cookieUtil;

    @Autowired
    private IVEmployeesService ivEmployeesService;


    @ApiOperation("模拟登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public CommonResult login(ServletRequest servletRequest, ServletResponse servletResponse, @RequestBody LoginDto loginDto) throws Exception {
        return userLoginService.login(servletRequest,servletResponse,loginDto);
    }


//    @RequestMapping(value = "/ssoLogin", method = RequestMethod.POST)
    @RequestMapping(value = "/SsoLogin", method = RequestMethod.POST)
    @ApiOperation("获取钉钉用户信息登录")
    public CommonResult ssoLogin(@RequestParam("code") String code,
                                 ServletResponse servletResponse) throws Exception {
        String sendBy="501905df0b924bea868011f70a6f4dc3";
        String loginForm = "{" +
                "\"code\":\"" + code + "\"," +
                "\"sendBy\":\""+ sendBy +"\"" +
                "}";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //code换取钉钉用户信息接口, 接口说明见下文
        HttpPost httpPost = new HttpPost("https://dingding.weichai.com/dingding/auth/sso");
        httpPost.setEntity(new StringEntity(loginForm, "utf-8"));
        httpPost.setHeader("Content-type", "application/json");
        CloseableHttpResponse response = httpClient.execute(httpPost);
        JSONObject userInfo = JSON.parseObject(EntityUtils.toString(response.getEntity()));
        try {
            response.close();
            httpPost.releaseConnection();
            httpClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        String username = userInfo.getJSONObject("body").getString("username");
//        System.out.println(username);
//        String job = userInfo.getJSONObject("body").getString("job_num");
//        System.out.println(job);
//        JSONObject body = userInfo.getJSONObject("body");
        LoginDto loginDto = new LoginDto();
        loginDto.setId(userInfo.getJSONObject("body").getString("job_num"));
        LambdaQueryWrapper<VEmployees> employeesLambdaQueryWrapper = new LambdaQueryWrapper<VEmployees>().eq(VEmployees::getUsercode, loginDto.getId());
        String username = ivEmployeesService.getOne(employeesLambdaQueryWrapper).getUsername();
        loginDto.setUsername(username);
        // 获得到username之后, 业务系统生成自己的Token或者Session，完成登录

        HttpServletResponse httpServletResponse =(HttpServletResponse) servletResponse;
        cookieUtil.token(httpServletResponse,loginDto);
        return CommonResult.success();
    }
}
