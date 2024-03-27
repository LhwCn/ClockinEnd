package com.itlozg.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itlozg.admin.entity.dto.LoginDto;
import com.itlozg.admin.util.CommonResult;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public interface IUserLoginService extends IService<LoginDto> {

    CommonResult login(ServletRequest servletRequest, ServletResponse servletResponse, LoginDto form);

    LoginDto loginByPhone(String username);
}
