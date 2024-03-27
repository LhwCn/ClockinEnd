package com.itlozg.admin.mapper;

import com.itlozg.admin.entity.SignGroup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SignGroupMapper extends BaseMapper<SignGroup> {
    List<SignGroup> getList();

    String getMsgNos(@Param("dateNow") String dateNow);
}
