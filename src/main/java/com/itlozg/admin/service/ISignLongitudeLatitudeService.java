package com.itlozg.admin.service;

import com.itlozg.admin.entity.SignLongitudeLatitude;
import com.itlozg.admin.model.response.SignLonLatInfoResponse;

import java.util.List;

public interface ISignLongitudeLatitudeService extends IBaseService<SignLongitudeLatitude> {
    public SignLonLatInfoResponse findFenceByRuleIdGroupBy(String ruleId);
    SignLonLatInfoResponse groupCovering(List<SignLongitudeLatitude> signLongitudeLatitudes);
}
