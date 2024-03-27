package com.itlozg.admin.model.request;


import cn.hutool.core.date.DateTime;

/**
 * 签到检查
 */
public class SignCheckRequest {
    /**
     * 打卡地点经度
     */
    private double longitude;

    /**
     * 打卡地点纬度
     */
    private double latitude;

    /**
     * 打卡时间
     */
    private DateTime signTime;

    /**
     * 打卡规则ID
     */
    private String signRuleId;

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
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
}
