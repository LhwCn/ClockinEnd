package com.itlozg.admin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * @program: lovol-mis
 * @description:
 * @author: lilu
 * @create: 2023-08-11 09:16
 **/
@Data
public class SignTemplate {
    private Integer id;
    @TableField("template_name")
    private String templateName;
    @TableField("rule_ids")
    private String ruleIds;
}
