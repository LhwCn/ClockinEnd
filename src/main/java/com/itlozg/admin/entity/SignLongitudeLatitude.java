package com.itlozg.admin.entity;



import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

@TableName("sign_longitude_latitude")
public class SignLongitudeLatitude extends TPBaseEntity implements Serializable {

    /**
     * 经度
     */
    private String longitude;

    /**
     * 纬度
     */
    private String latitude;

    /**
     * 打卡规则id
     */
    @TableField("rule_id")
    private String ruleId;

    /**
     * 围栏顺序
     */
    @TableField("fence_order")
    private int fenceOrder;

    /**
     * 圆形电子围栏半径
     */
    private String radius;

    /**
     * 覆盖区域组id
     */
    @TableField("group_id")
    private String groupId;

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

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    public int getFenceOrder() {
        return fenceOrder;
    }

    public void setFenceOrder(int fenceOrder) {
        this.fenceOrder = fenceOrder;
    }

    public String getRadius() {
        return radius;
    }

    public void setRadius(String radius) {
        this.radius = radius;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
