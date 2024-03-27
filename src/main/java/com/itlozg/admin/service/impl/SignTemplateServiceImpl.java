package com.itlozg.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itlozg.admin.entity.SignTemplate;
import com.itlozg.admin.mapper.SignTemplateMapper;
import com.itlozg.admin.service.SignTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignTemplateServiceImpl extends ServiceImpl<SignTemplateMapper, SignTemplate> implements SignTemplateService {
    @Autowired
    private SignTemplateMapper signTemplateMapper;
    @Override
    public String getSpecialTemplate(String userId, String groupId) {
        return signTemplateMapper.getSpecialTemplate(userId,groupId);
    }

    @Override
    public String getNormalTemplate(String templateGroup) {
        return signTemplateMapper.getNormalTemplate(templateGroup);
    }
}
