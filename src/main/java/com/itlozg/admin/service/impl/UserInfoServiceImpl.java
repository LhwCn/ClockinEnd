package com.itlozg.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itlozg.admin.entity.User;
import com.itlozg.admin.mapper.UserInfoMapper;
import com.itlozg.admin.service.UserInfoService;
import com.itlozg.admin.util.CommonResult;
import org.springframework.stereotype.Service;

/**
 * @program: LOVOL-Clock-End
 * @description: 用户信息
 * @author: lilu
 * @create: 2023-07-14 10:02
 **/
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, User> implements UserInfoService {

    @Override
    public CommonResult getList() {
        Page<User> page = new Page<>();
        this.page(page,null);
        return CommonResult.success(page);
    }
}
