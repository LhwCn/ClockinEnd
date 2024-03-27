package com.itlozg.admin.entity;




import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;

@TableName("sign_rule")
public class SignRule extends TPBaseEntity implements Serializable {

    /**
     * 规则类型：0 固定时间上下班 1 自由上下班
     */
    @TableField("rule_type")
    private String ruleType;

    /**
     * 规则名称
     */
    private String name;

    /**
     * 地点名称
     */
    @TableField("address_name")
    private String addressName;

    /**
     * 工作日
     */
    @TableField("work_days")
    private String workDays;

    /**
     * 上班时间
     */
    @TableField("work_time")
    private String workTime;

    /**
     * 上午下班时间
     */
    @TableField("am_off_time")
    private String amOffTime;

    /**
     * 下午上班时间
     */
    @TableField("pm_work_time")
    private String pmWorkTime;

    /**
     * 下班时间
     */
    @TableField("off_time")
    private String offTime;

    /**
     * 打卡提醒时间
     */
    @TableField("tip_time")
    private String tipTime;

    /**
     * 打卡提醒内容
     */
    @TableField("tip_msg")
    private String tipMsg;

    /**
     * 位置打卡：0启用1禁用
     */
    @TableField("location_clock")
    private String locationClock;

    /**
     * 拍照打卡：0启用1停用
     */
    @TableField("photograph_clock")
    private String photographClock;

    /**
     * 状态0启用1停用
     */
    @TableField("status")
    private String status;

    /**
     * 跨天 0：不跨天 1：跨天
     */
    @TableField("cross_day")
    private String crossDay;

    /**
     * 是否四次打卡0：否  1：是
     */
    @TableField("is_quartic")
    private String isQuartic;

    /**
     * 用户ID
     */
    @TableField(exist = false)
    private String userId;

    /**
     * 是否开启上班打卡人脸识别  0：否 1：是
     */
    @TableField("COME_VERIFACE")
    private String comeVeriface;

    /**
     * 是否开启下班打卡人脸识别  0：否 1：是
     */
    @TableField("LEAVE_VERIFACE")
    private String leaveVeriface;

    /**
     * 是否可以修改请假时长  0：否 1：是
     */
    @TableField("IS_EDIT_TIMELENGTH")
    private String isEditTimelength;

    /**
     * 应上班工时(自由打卡调整)
     */
    @TableField("WORK_HOURS")
    private BigDecimal workHours;

    /**
     * 跨天打卡结束时间
     */
    @TableField("end_time")
    private String endTime;

    /**
     * 禁用手动打卡
     */
    @TableField("handpunch")
    private String handpunch;
    @TableField("rang_id")
    private String rangeId;

    @TableField(exist = false)
    private String rangeName;

    @TableField(exist = false)
    private String railName;

    public String getRailName() {
        return railName;
    }

    public void setRailName(String railName) {
        this.railName = railName;
    }

    public String getRangeId() {
        return rangeId;
    }

    public void setRangeId(String rangeId) {
        this.rangeId = rangeId;
    }

    public String getRangeName() {
        return rangeName;
    }

    public void setRangeName(String rangeName) {
        this.rangeName = rangeName;
    }

    public String getRuleType() {
        return ruleType;
    }

    public void setRuleType(String ruleType) {
        this.ruleType = ruleType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWorkDays() {
        return workDays;
    }

    public void setWorkDays(String workDays) {
        this.workDays = workDays;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public String getOffTime() {
        return offTime;
    }

    public void setOffTime(String offTime) {
        this.offTime = offTime;
    }

    public String getTipTime() {
        return tipTime;
    }

    public void setTipTime(String tipTime) {
        this.tipTime = tipTime;
    }

    public String getTipMsg() {
        return tipMsg;
    }

    public void setTipMsg(String tipMsg) {
        this.tipMsg = tipMsg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLocationClock() {
        return locationClock;
    }

    public void setLocationClock(String locationClock) {
        this.locationClock = locationClock;
    }

    public String getPhotographClock() {
        return photographClock;
    }

    public void setPhotographClock(String photographClock) {
        this.photographClock = photographClock;
    }

    public String getCrossDay() {
        return crossDay;
    }

    public void setCrossDay(String crossDay) {
        this.crossDay = crossDay;
    }

    public String getAmOffTime() {
        return amOffTime;
    }

    public void setAmOffTime(String amOffTime) {
        this.amOffTime = amOffTime;
    }

    public String getPmWorkTime() {
        return pmWorkTime;
    }

    public void setPmWorkTime(String pmWorkTime) {
        this.pmWorkTime = pmWorkTime;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getIsQuartic() {
        return isQuartic;
    }

    public void setIsQuartic(String isQuartic) {
        this.isQuartic = isQuartic;
    }

    public String getComeVeriface() {
        return comeVeriface;
    }

    public void setComeVeriface(String comeVeriface) {
        this.comeVeriface = comeVeriface;
    }

    public String getLeaveVeriface() {
        return leaveVeriface;
    }

    public void setLeaveVeriface(String leaveVeriface) {
        this.leaveVeriface = leaveVeriface;
    }

    public BigDecimal getWorkHours() {
        return workHours;
    }

    public void setWorkHours(BigDecimal workHours) {
        this.workHours = workHours;
    }

    public String getIsEditTimelength() {
        return isEditTimelength;
    }

    public void setIsEditTimelength(String isEditTimelength) {
        this.isEditTimelength = isEditTimelength;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getHandpunch() {
        return handpunch;
    }

    public void setHandpunch(String handpunch) {
        this.handpunch = handpunch;
    }
}
