package com.itlozg.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.itlozg.admin.entity.SignFinal;
import com.itlozg.admin.mapper.SignFinalMapper;
import com.itlozg.admin.service.ISignFinalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class SignFinalServiceImpl extends BaseServiceImpl<SignFinalMapper, SignFinal> implements ISignFinalService {


    @Override
    public SignFinal getByUserId(String userId) {
        return baseMapper.getByUserId(userId);
    }
}
