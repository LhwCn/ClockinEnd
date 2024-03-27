package com.itlozg.admin.service;

import com.itlozg.admin.entity.SignNextAll;
import com.itlozg.admin.model.request.SignNextAllRequest;
import com.itlozg.admin.model.response.SignNextAllResponse;

import java.util.List;

public interface ISignNextAllService extends IBaseService<SignNextAll> {

    /**
     * 获取打卡明细
     *
     * @param userId
     * @param signDay
     * @return
     */
    public List<SignNextAllResponse> getSignDetailByUserDay(String userId, String signDay);

    /**
     * 保存打卡信息
     */
    public boolean saveSignInfo(SignNextAllRequest request);

    public List<SignNextAllResponse> getSignDetailByNextId(String nextId);
}
