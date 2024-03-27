package com.itlozg.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itlozg.admin.entity.SignTemplate;

public interface SignTemplateService extends IService<SignTemplate> {
    String getSpecialTemplate(String userId,String groupId);

    /**
     * 根据考勤组与模板关系表的ID，获取对应的模板中的考勤规则集合
     */
    String getNormalTemplate(String templateGroup);
}
