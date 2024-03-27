package com.itlozg.admin.entity;



import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
/*
* 与设置打卡规则有关的内容
* */
@TableName("sign_final")
public class SignFinal extends TPBaseEntity implements Serializable {

    /**
     * 人员id
     */
    @TableField("user_id")
    private String userId;

    /**
     * 打卡规则id
     */
    @TableField("rule_id")
    private String ruleId;

    @TableField(exist = false)
    private String userName;
    @TableField(exist = false)
    private String userNo;
    @TableField(exist = false)
    private String officeName;
    @TableField(exist = false)
    private String deptName;
    @TableField(exist = false)
    private String companyName;
    @TableField(exist = false)
    private int isType;//区分是已设置还是为设置打卡规则   0：未设置（设置）  1：已设置（编辑）

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getIsType() {
        return isType;
    }

    public void setIsType(int isType) {
        this.isType = isType;
    }
}
