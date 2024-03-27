package com.itlozg.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itlozg.admin.entity.SignTemplate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SignTemplateMapper extends BaseMapper<SignTemplate> {
    String getSpecialTemplate(@Param("userId") String userId, @Param("groupId") String groupId);

    String getNormalTemplate(@Param("templateGroup") String templateGroup);
}
