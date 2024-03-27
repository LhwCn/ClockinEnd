package com.itlozg.admin.mapper;

import com.itlozg.admin.entity.SignNext;
import com.itlozg.admin.entity.dto.SignNextDto;
import com.itlozg.admin.entity.vo.SignNextVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SignNextMapper extends BaseMapper<SignNext> {
    public SignNext getSignInfoByUserDay(@Param("dayVal") String dayVal, @Param("userId") String userId);

    public List<SignNext> getSignInfoByUserToday(@Param("dateBefore") String dateBefore, @Param("dateNow") String dateNow, @Param("userId") String userId);

    public List<SignNext> getSignInfoByUserTodayOnlyInfo(@Param("dateBefore") String dateBefore, @Param("dateNow") String dateNow, @Param("userId") String userId);

    List<SignNextVO> getSignInfoAllToday(@Param("groupId") String groupId);

    void autoSave();

    Boolean autoSaveCarInfo(SignNext signNext);

    List<SignNextVO> getAutoNextInfo();

    List<SignNextVO> getSureUser();

    List<SignNextVO> getFreeUser();
}
