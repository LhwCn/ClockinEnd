package com.itlozg.admin.model.response;


import cn.hutool.core.date.DateTime;

/**
 * 签到检查反馈
 */
public class SignCheckResponse {
    /**
     * 打卡地点经度
     */
    private String longitude;

    /**
     * 打卡地点纬度
     */
    private String latitude;

    /**
     * 打卡时间
     */
    private DateTime signTime;

    /**
     * 打卡规则ID
     */
    private String signRuleId;

    /**
     * 是否可以打卡
     */
    private boolean canSign;

    /**
     * 警告信息
     */
    private String warnInfo;

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public DateTime getSignTime() {
        return signTime;
    }

    public void setSignTime(DateTime signTime) {
        this.signTime = signTime;
    }

    public String getSignRuleId() {
        return signRuleId;
    }

    public void setSignRuleId(String signRuleId) {
        this.signRuleId = signRuleId;
    }

    public boolean isCanSign() {
        return canSign;
    }

    public void setCanSign(boolean canSign) {
        this.canSign = canSign;
    }

    public String getWarnInfo() {
        return warnInfo;
    }

    public void setWarnInfo(String warnInfo) {
        this.warnInfo = warnInfo;
    }
}
