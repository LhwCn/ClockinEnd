package com.itlozg.admin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

@TableName("sign_white")
public class SignWhite extends TPBaseEntity implements Serializable {

    /**
     * 用户id
     */
    @TableField("user_id")
    private String userId;

    /**
     * 打卡规则id
     */
    @TableField("rule_id")
    private String ruleId;

    /**
     * 白名单人员
     * */
    @TableField(exist = false)
    private String whiteName;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    public String getWhiteName() {
        return whiteName;
    }

    public void setWhiteName(String whiteName) {
        this.whiteName = whiteName;
    }
}
