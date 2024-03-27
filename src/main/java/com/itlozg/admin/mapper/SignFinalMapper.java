package com.itlozg.admin.mapper;

import com.itlozg.admin.entity.SignFinal;
import org.apache.ibatis.annotations.Param;
public interface SignFinalMapper extends BaseMapper<SignFinal> {
    public SignFinal getByUserId(@Param("userId") String userId);
}
