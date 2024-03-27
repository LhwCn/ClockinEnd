package com.itlozg.admin.mapper;

import com.itlozg.admin.entity.SignLegwork;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SignLegworkMapper extends BaseMapper<SignLegwork> {
    public List<SignLegwork> getListByUserDay(@Param("userId") String userId, @Param("signDay") String signDay);
}
