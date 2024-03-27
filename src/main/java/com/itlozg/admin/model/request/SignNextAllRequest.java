package com.itlozg.admin.model.request;


import java.util.Date;

public class SignNextAllRequest extends BaseRequest {

    /**
     * 所属签到id
     */
    private String signId;

    /**
     * 签到日期
     */
    private Date signDate;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 打卡类型:0 上班 1 下班
     */
    private String signType;

    /**
     * 打卡时间
     */
    private String signTime;

    /**
     * 打卡规则类型 0：固定 1：自由
     */
    private String ruleType;

    /**
     * 打卡规则id
     */
    private String ruleId;

    /**
     * 打卡地点经度
     */
    private String signLongitude;

    /**
     * 打卡地点纬度
     */
    private String signLatitude;

    /**
     * 打卡地址
     */
    private String signAddress;

    /**
     * 打卡地址--名称
     */
    private String signAddressName;

    /**
     * 打卡备注
     */
    private String signRemarks;

    /**
     * 打卡图片
     */
    private String signPhoto;

    /**
     * 打卡方式 0：正常打卡 1：自动打卡
     */
    private String signWay;

    /**
     * 下班打卡方式 0：正常打卡 1：自动打卡
     */
    private String signWayPm;

    /**
     * rootval
     * 默认空着就行
     * android  root  1
     * ios          越狱   2
     */
    private String rootVal;

    /**
     * 打卡设备编号
     */
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

    public String getSignPhoto() {
        return signPhoto;
    }

    public void setSignPhoto(String signPhoto) {
        this.signPhoto = signPhoto;
    }

    public String getSignAddress() {
        return signAddress;
    }

    public void setSignAddress(String signAddress) {
        this.signAddress = signAddress;
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

    public String getUniqueCode() {
        return uniqueCode;
    }

    public void setUniqueCode(String uniqueCode) {
        this.uniqueCode = uniqueCode;
    }
}
