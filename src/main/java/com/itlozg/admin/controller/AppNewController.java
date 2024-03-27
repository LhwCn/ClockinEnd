package com.itlozg.admin.controller;

import com.itlozg.admin.entity.dto.LoginDto;
import com.itlozg.admin.util.CommonResult;
import com.itlozg.admin.util.ThreadLocalUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/appnew")
public class AppNewController {
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    public CommonResult getUserInfo () throws Exception {
        LoginDto loginDto = ThreadLocalUtil.get();
        loginDto.setPassword(null);
        if(loginDto != null && StringUtils.isNotEmpty(loginDto.getUsername())){
            return CommonResult.success(loginDto);
        }
        return CommonResult.failed("获取用户信息失败");
    }
}
