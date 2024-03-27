package com.itlozg.admin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

@TableName("sign_legal_leave")
public class SignLegalLeave extends TPBaseEntity implements Serializable {

    /**
     * 节假日名称
     */
    private String name;

    /**
     * 打卡规则id
     */
    @TableField("rule_id")
    private String ruleId;

    /**
     * 日期
     */
    @TableField("leave_date")
    private Date leaveDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    public Date getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(Date leaveDate) {
        this.leaveDate = leaveDate;
    }
}
