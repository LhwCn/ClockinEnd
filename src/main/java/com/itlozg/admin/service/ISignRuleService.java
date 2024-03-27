package com.itlozg.admin.service;

import com.itlozg.admin.entity.SignRule;
import com.itlozg.admin.entity.dto.SelectRulePageDto;
import com.itlozg.admin.model.response.SignRuleResponse;
import com.itlozg.admin.util.CommonResult;

public interface ISignRuleService extends IBaseService<SignRule> {

    public SignRuleResponse getByRuleId(String id);

    CommonResult selectRuleList(SelectRulePageDto selectRulePageDto);
}
