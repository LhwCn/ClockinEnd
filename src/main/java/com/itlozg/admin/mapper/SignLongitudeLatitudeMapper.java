package com.itlozg.admin.mapper;

import com.itlozg.admin.entity.SignLongitudeLatitude;

import java.util.List;

public interface SignLongitudeLatitudeMapper extends BaseMapper<SignLongitudeLatitude> {
    public List<SignLongitudeLatitude> findFenceByRuleId(String ruleId);
}
