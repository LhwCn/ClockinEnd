package com.itlozg.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itlozg.admin.entity.User;
import com.itlozg.admin.util.CommonResult;

public interface UserInfoService extends IService<User> {
    CommonResult getList();
}
