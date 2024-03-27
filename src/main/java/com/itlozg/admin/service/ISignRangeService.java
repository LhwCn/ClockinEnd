package com.itlozg.admin.service;

import com.itlozg.admin.entity.SignLongitudeLatitude;
import com.itlozg.admin.entity.SignRange;
import com.itlozg.admin.model.response.SignLonLatInfoResponse;

import java.util.List;

public interface ISignRangeService extends IBaseService<SignRange>{

    SignLonLatInfoResponse findFenceByRuleIdGroupBy(String ruleIdVal);
    SignLonLatInfoResponse groupCovering(List<SignLongitudeLatitude> signLongitudeLatitudes);
}
