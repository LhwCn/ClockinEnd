package com.itlozg.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itlozg.admin.entity.VEmployees;
import com.itlozg.admin.entity.dto.LoginDto;
import com.itlozg.admin.mapper.UserLoginMapper;
import com.itlozg.admin.mapper.VEmployeesMapper;
import com.itlozg.admin.service.IUserLoginService;
import com.itlozg.admin.util.CommonResult;
import com.itlozg.admin.util.CookieUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

@Service
@RequiredArgsConstructor
@Slf4j
/*
用户登录service
* */
public class UserLoginServiceImpl extends ServiceImpl<UserLoginMapper,LoginDto> implements IUserLoginService {
    /*
    * 注入用户mapper
    * */
    @Autowired
    private UserLoginMapper userLoginMapper;
    @Autowired
    private CookieUtil cookieUtil;

    @Autowired
    private VEmployeesMapper vEmployeesMapper;

    @Override
    public CommonResult login(ServletRequest servletRequest, ServletResponse servletResponse, LoginDto form) {
        if(StringUtils.isEmpty(form.getId())){
            return CommonResult.failed("员工号不能为空");
        }
        if(StringUtils.isEmpty(form.getPassword())){
            return CommonResult.failed("密码不能为空");
        }
        LambdaQueryWrapper<LoginDto> loginDtoLambdaQueryWrapper = new LambdaQueryWrapper<>();
        loginDtoLambdaQueryWrapper.eq(LoginDto::getId,form.getId());
        LoginDto loginDto = userLoginMapper.selectOne(loginDtoLambdaQueryWrapper);
//        String s = DigestUtils.md5Hex(form.getPassword());
//        System.out.println(s);
        if(loginDto==null){
            return CommonResult.failed("手机号不正确");
        }else if(form.getPassword().equals(loginDto.getPassword())){
            HttpServletResponse response =(HttpServletResponse) servletResponse;
            String username = vEmployeesMapper.selectOne(new LambdaQueryWrapper<VEmployees>().eq(VEmployees::getUsercode, loginDto.getId())).getUsername();
            loginDto.setUsername(username);
            cookieUtil.token(response,loginDto);
            return CommonResult.success(loginDto);
        }else{
            return CommonResult.failed("密码不正确");
        }
    }

    @Override
    public LoginDto loginByPhone(String id) {
        LambdaQueryWrapper<LoginDto> loginDtoLambdaQueryWrapper = new LambdaQueryWrapper<>();
        loginDtoLambdaQueryWrapper.eq(LoginDto::getId,id);
        LoginDto loginDto = userLoginMapper.selectOne(loginDtoLambdaQueryWrapper);
        return loginDto;
    }
}
