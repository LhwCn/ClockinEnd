package com.itlozg.admin.entity.dto;

import lombok.Data;

@Data
public class SelectRulePageDto extends PageDto{
    //规则名称
    private String ruleName;
    //公司名称
    private String companyName;
}
