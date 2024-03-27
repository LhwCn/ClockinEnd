package com.itlozg.admin.mapper;

import com.itlozg.admin.entity.SignNextAll;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SignNextAllMapper extends BaseMapper<SignNextAll> {
    public List<SignNextAll> getSignDetailByUserDay(@Param("userId") String userId, @Param("signDay") String signDay);

    public List<SignNextAll> getSignDetailByNextId(@Param("nextId") String nextId);
}
