package com.itlozg.admin.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itlozg.admin.entity.SignFile;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SignFileMapper extends BaseMapper<SignFile> {
    List<SignFile> getSignInfoByUserMonth(@Param("userId") String userId, @Param("month") String month);

    int applyRepairSign(@Param("userId") String userId, @Param("date") String date);

    int applyLeaveSign(@Param("userId") String userId, @Param("date") String date);

    SignFile getSignInfoByUserDay(@Param("dayVal") String dayVal, @Param("userId") String userId);
    SignFile getSignFileByUserDay(@Param("dayVal") String dayVal, @Param("userId") String userId);


    Page<SignFile> getSignInfoToPage(Page<SignFile> signFilePage,@Param("username") String username,@Param("deptname") String deptname,@Param("startTime") String startTime,@Param("endTime") String endTime);
}
