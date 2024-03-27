package com.itlozg.admin.service;

import com.itlozg.admin.entity.SignLegwork;
import com.itlozg.admin.model.request.SignLegworkRequest;
import com.itlozg.admin.model.response.SignLegworkResponse;

import java.util.List;

public interface ISignLegworkService extends IBaseService<SignLegwork> {


    /*
    *获取打卡明细列表
    * */
    public List<SignLegworkResponse> getListByUserDay(String userId, String signDay);

    /**
     * 出差打卡
     * 保存打卡信息
     */
    public boolean saveSignInfo(SignLegworkRequest request);

    List<SignLegwork> getSignInfoAllLastday(String groupId);
}
