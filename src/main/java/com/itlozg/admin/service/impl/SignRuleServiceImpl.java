package com.itlozg.admin.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itlozg.admin.entity.SignRule;
import com.itlozg.admin.entity.dto.SelectRulePageDto;
import com.itlozg.admin.mapper.SignRuleMapper;
import com.itlozg.admin.service.ISignRuleService;
import com.itlozg.admin.model.response.SignRuleResponse;
import com.itlozg.admin.util.BeanCopier;
import com.itlozg.admin.util.CommonResult;
import com.itlozg.admin.util.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class SignRuleServiceImpl extends BaseServiceImpl<SignRuleMapper, SignRule> implements ISignRuleService {

    /*
    * 获取打卡规则id
    * */
    @Override
    public SignRuleResponse getByRuleId(String id) {
        SignRule rules = baseMapper.findByRuleId(id);
        if (rules != null) {
            SignRuleResponse responses = BeanCopier.copy(rules, SignRuleResponse.class);
            return responses;
        } else {
            return null;
        }
    }

    @Override
    public CommonResult selectRuleList(SelectRulePageDto selectRulePageDto) {
        String ruleName = null;
        String companyName = null;
        if(StringUtils.isNotBlank(selectRulePageDto.getRuleName())){
            ruleName = selectRulePageDto.getRuleName();
        }
        if(StringUtils.isNotBlank(selectRulePageDto.getCompanyName())){
            companyName = selectRulePageDto.getCompanyName();
        }
        Page<SignRule> signRulePage = new Page<>();
        if(StringUtils.isNotBlank(selectRulePageDto.getPageNum()) && StringUtils.isNotBlank(selectRulePageDto.getPageSize())){
            int current = Integer.valueOf(selectRulePageDto.getPageNum());
            int size = Integer.valueOf(selectRulePageDto.getPageSize());
            signRulePage.setCurrent(current);
            signRulePage.setSize(size);
        }
        Page<SignRule> result = baseMapper.selectRuleList(signRulePage,ruleName,companyName);
        return CommonResult.success(result);
    }
}
