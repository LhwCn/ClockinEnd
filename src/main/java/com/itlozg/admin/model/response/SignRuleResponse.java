package com.itlozg.admin.model.response;

import com.itlozg.admin.entity.SignGroup;
import com.itlozg.admin.entity.SignLegalLeave;
import com.itlozg.admin.entity.SignLongitudeLatitude;
import com.itlozg.admin.entity.SignWhite;

import java.math.BigDecimal;
import java.util.List;

public class SignRuleResponse extends BaseResponse {

    // 规则类型:0固定时间上下班1自由上下班
    private String ruleType;

    /**
     * 规则名称
     */
    private String name;

    /**
     * 地点名称
     */
    private String addressName;

    /**
     * 工作日
     */
    private String workDays;

    /**
     * 上班时间
     * 工作时间
     */
    private String workTime;

    /**
     * 上午下班时间
     */
    private String amOffTime;

    /**
     * 下午上班时间
     */
    private String pmWorkTime;

    /**
     * 下班时间
     */
    private String offTime;

    /**
     * 打卡提醒时间
     */
    private String tipTime;

    /**
     * 打卡提醒内容
     */
    private String tipMsg;

    /**
     * 位置打卡：0启用1禁用
     */
    private String locationClock;

    /**
     * 拍照打卡：0启用1停用
     */
    private String photographClock;

    /**
     * 状态0启用1停用
     */
    private String status;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 白名单人员
     */
    private List<SignWhite> userName;

    /**
     * 节假日
     */
    private List<SignLegalLeave> legalLeaves;

    /**
     * 跨天 0：不跨天 1：跨天
     */
    private String crossDay;

    /**
     * 考勤组
     */
    private List<SignGroup> signGroups;

    /**
     * 电子围栏
     */
    private List<SignLongitudeLatitude> signLoLa;

    /**
     * 是否四次打卡0：否  1：是
     */
    private String isQuartic;

    /**
     * 是否开启上班打卡人脸识别  0：否 1：是
     */
    private String comeVeriface;

    /**
     * 是否开启下班打卡人脸识别  0：否 1：是
     */
    private String leaveVeriface;

    /**
     * 请假是否可修改时长
     */
    private String isEditTimelength;

    /**
     * 应上班工时(自由打卡调整)
     */
    private BigDecimal workHours;

    /**
     * 跨天打卡结束时间
     */
    private String endTime;

    private String handpunch;

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

    public List<SignWhite> getUserName() {
        return userName;
    }

    public void setUserName(List<SignWhite> userName) {
        this.userName = userName;
    }

    public List<SignLegalLeave> getLegalLeaves() {
        return legalLeaves;
    }

    public void setLegalLeaves(List<SignLegalLeave> legalLeaves) {
        this.legalLeaves = legalLeaves;
    }

    public List<SignGroup> getSignGroups() {
        return signGroups;
    }

    public void setSignGroups(List<SignGroup> signGroups) {
        this.signGroups = signGroups;
    }

    public List<SignLongitudeLatitude> getSignLoLa() {
        return signLoLa;
    }

    public void setSignLoLa(List<SignLongitudeLatitude> signLoLa) {
        this.signLoLa = signLoLa;
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

    @Override
    public String toString() {
        return "SignRuleResponse{" +
                "ruleType='" + ruleType + '\'' +
                ", name='" + name + '\'' +
                ", addressName='" + addressName + '\'' +
                ", workDays='" + workDays + '\'' +
                ", workTime='" + workTime + '\'' +
                ", amOffTime='" + amOffTime + '\'' +
                ", pmWorkTime='" + pmWorkTime + '\'' +
                ", offTime='" + offTime + '\'' +
                ", tipTime='" + tipTime + '\'' +
                ", tipMsg='" + tipMsg + '\'' +
                ", locationClock='" + locationClock + '\'' +
                ", photographClock='" + photographClock + '\'' +
                ", status='" + status + '\'' +
                ", userId='" + userId + '\'' +
                ", userName=" + userName +
                ", legalLeaves=" + legalLeaves +
                ", crossDay='" + crossDay + '\'' +
                ", signGroups=" + signGroups +
                ", signLoLa=" + signLoLa +
                ", isQuartic='" + isQuartic + '\'' +
                ", comeVeriface='" + comeVeriface + '\'' +
                ", leaveVeriface='" + leaveVeriface + '\'' +
                ", isEditTimelength='" + isEditTimelength + '\'' +
                ", workHours=" + workHours +
                ", endTime='" + endTime + '\'' +
                ", handpunch='" + handpunch + '\'' +
                '}';
    }
}
