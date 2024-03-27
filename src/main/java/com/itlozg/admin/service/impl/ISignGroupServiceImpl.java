package com.itlozg.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itlozg.admin.entity.SignGroup;
import com.itlozg.admin.mapper.SignGroupMapper;
import com.itlozg.admin.service.ISignGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ISignGroupServiceImpl extends ServiceImpl<SignGroupMapper,SignGroup> implements ISignGroupService {
    @Autowired
    SignGroupMapper signGroupMapper;

    @Override
    public List<SignGroup> getList() {
        List<SignGroup> signGroups = signGroupMapper.getList();
        return signGroups;
    }

    @Override
    public String getMsgNos(String dateNow) {
        String msgNos = signGroupMapper.getMsgNos(dateNow);
        return msgNos;
    }
}
