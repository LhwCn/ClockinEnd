package com.itlozg.admin.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itlozg.admin.entity.SignRule;
import org.apache.ibatis.annotations.Param;

public interface SignRuleMapper extends BaseMapper<SignRule> {
    public SignRule findByRuleId(String id);

    Page<SignRule> selectRuleList(Page<SignRule> signRulePage, @Param("ruleName") String ruleName, @Param("companyName") String companyName);
}
