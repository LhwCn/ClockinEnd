package com.itlozg.admin.service;

import cn.hutool.crypto.asymmetric.Sign;
import com.itlozg.admin.entity.SignNext;
import com.itlozg.admin.entity.dto.SignNextDto;
import com.itlozg.admin.entity.vo.SignNextVO;
import com.itlozg.admin.model.request.SignNextRequest;
import com.itlozg.admin.model.response.SignNextResponse;
import com.itlozg.admin.model.response.SignRuleResponse;

import java.util.List;

public interface ISignNextService extends IBaseService<SignNext> {

    /**
     * 用户用户打卡信息
     *
     * @param userId
     * @param dayVal
     * @return
     */
    public SignNextResponse getSignInfoByUserDay(String userId, String dayVal);

    /**
     * 获取打卡信息
     *
     * @param nextId
     * @return
     */
    public SignNextResponse getSignNextById(String nextId);

    /**
     * 保存打卡信息  后台处理上下班逻辑
     *
     * @param userId
     * @param request
     * @param signRule
     * @return
     */
    public SignNextResponse saveSignInfoNew(String userId, SignNextRequest request, SignRuleResponse signRule);

    /**
     * 获取当天打卡信息
     **/
    public SignNextResponse getSignInfoByUserToday(String userId);

    /**
     * 获取当天打卡信息(处理凌晨跨天)
     **/
    public SignNextResponse getSignInfoByUserToday(String userId, boolean isNeedDeal);

    public SignNextResponse getSignInfoByUserTodayOnlyInfo(String userId);


    /**
     * 获取打卡信息  前一天
     *
     * @param userId
     * @return
     */
    public SignNext getSignInfoByUserLastday(String userId);

    /**
     * 获取考勤组昨天的所有考勤计划
     */
    public List<SignNextVO> getSignInfoAllLastday(String groupId);

    void autoSave();

    Boolean autoSaveCarInfo(SignNext signNext);

    /**
     * 查询考勤人员的信息，并填充一些信息，拼装成一个考勤计划
     */
    List<SignNextVO> getAutoNextInfo();

    List<SignNextVO> getSureUser();

    List<SignNextVO> getFreeUser();
}
