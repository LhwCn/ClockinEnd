package com.itlozg.admin.mapper;

import org.apache.ibatis.annotations.Param;

public interface BaseMapper<T> extends com.baomidou.mybatisplus.core.mapper.BaseMapper<T> {

    /**
     * 删除信息
     *
     * @param id
     * @param delFlag
     */
    public int deleteData(@Param("id") String id, @Param("DEL_FLAG_DELETE") String delFlag);
}
