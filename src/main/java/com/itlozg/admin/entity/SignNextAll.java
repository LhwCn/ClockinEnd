package com.itlozg.admin.entity;



import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

@TableName("sign_next_all")
public class SignNextAll extends TPBaseEntity implements Serializable {
    @Override
    public String toString() {
        return "SignNextAll{" +
                "signId='" + signId + '\'' +
                ", signDate=" + signDate +
                ", userId='" + userId + '\'' +
                ", signType='" + signType + '\'' +
                ", signTime='" + signTime + '\'' +
                ", ruleType='" + ruleType + '\'' +
                ", ruleId='" + ruleId + '\'' +
                ", signLongitude='" + signLongitude + '\'' +
                ", signLatitude='" + signLatitude + '\'' +
                ", signAddress='" + signAddress + '\'' +
                ", signAddressName='" + signAddressName + '\'' +
                ", signRemarks='" + signRemarks + '\'' +
                ", signWay='" + signWay + '\'' +
                ", signWayPm='" + signWayPm + '\'' +
                ", rootVal='" + rootVal + '\'' +
                ", uniqueCode='" + uniqueCode + '\'' +
                '}';
    }

    /**
     * 所属签到id
     */
    @TableField("sign_id")
    private String signId;

    /**
     * 签到日期
     */
    @TableField("sign_date")
    private Date signDate;
    /**
     * 用户id
     */
    @TableField("user_id")
    private String userId;

    /**
     * 打卡类型:0 上班 1 下班
     */
    @TableField("sign_type")
    private String signType;

    /**
     * 打卡时间
     */
    @TableField("sign_time")
    private String signTime;

    /**
     * 打卡规则类型 0：固定 1：自由
     */
    @TableField("rule_type")
    private String ruleType;

    /**
     * 打卡规则id
     */
    @TableField("rule_id")
    private String ruleId;

    /**
     * 打卡地点经度
     */
    @TableField("sign_longitude")
    private String signLongitude;

    /**
     * 打卡地点纬度
     */
    @TableField("sign_latitude")
    private String signLatitude;

    /**
     * 打卡地址
     */
    @TableField("sign_address")
    private String signAddress;

    /**
     * 打卡地址--名称
     */
    @TableField("sign_addressname")
    private String signAddressName;

    /**
     * 打卡备注
     */
    @TableField("sign_remarks")
    private String signRemarks;

    /**
     * 打卡方式 0：正常打卡 1：自动打卡
     */
    @TableField("sign_way")
    private String signWay;

    /**
     * 下班打卡方式 0：正常打卡 1：自动打卡
     */
    @TableField("sign_way_pm")
    private String signWayPm;

    /**
     * rootval
     * 默认空着就行
     * android  root  1
     * ios          越狱   2
     */
    @TableField("root_val")
    private String rootVal;

    /**
     * 打卡设备编号
     */
    @TableField("unique_code")
    private String uniqueCode;

    public String getSignId() {
        return signId;
    }

    public void setSignId(String signId) {
        this.signId = signId;
    }

    public Date getSignDate() {
        return signDate;
    }

    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getSignTime() {
        return signTime;
    }

    public void setSignTime(String signTime) {
        this.signTime = signTime;
    }

    public String getRuleType() {
        return ruleType;
    }

    public void setRuleType(String ruleType) {
        this.ruleType = ruleType;
    }

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    public String getSignLongitude() {
        return signLongitude;
    }

    public void setSignLongitude(String signLongitude) {
        this.signLongitude = signLongitude;
    }

    public String getSignLatitude() {
        return signLatitude;
    }

    public void setSignLatitude(String signLatitude) {
        this.signLatitude = signLatitude;
    }


    public String getSignAddress() {
        return signAddress;
    }

    public void setSignAddress(String signAddress) {
        this.signAddress = signAddress;
    }

    public String getSignAddressName() {
        return signAddressName;
    }

    public void setSignAddressName(String signAddressName) {
        this.signAddressName = signAddressName;
    }

    public String getSignRemarks() {
        return signRemarks;
    }

    public void setSignRemarks(String signRemarks) {
        this.signRemarks = signRemarks;
    }

    public String getSignWay() {
        return signWay;
    }

    public void setSignWay(String signWay) {
        this.signWay = signWay;
    }

    public String getSignWayPm() {
        return signWayPm;
    }

    public void setSignWayPm(String signWayPm) {
        this.signWayPm = signWayPm;
    }

    public String getRootVal() {
        return rootVal;
    }

    public void setRootVal(String rootVal) {
        this.rootVal = rootVal;
    }

    public String getUniqueCode() {
        return uniqueCode;
    }

    public void setUniqueCode(String uniqueCode) {
        this.uniqueCode = uniqueCode;
    }
}
