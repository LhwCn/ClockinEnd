package com.itlozg.admin.mapper;

import com.itlozg.admin.entity.SignLongitudeLatitude;
import com.itlozg.admin.entity.SignRange;

import java.util.List;

public interface SignRangeMapper extends BaseMapper<SignRange> {
    public List<SignLongitudeLatitude> findFenceByRuleId(String rangeId);
}
