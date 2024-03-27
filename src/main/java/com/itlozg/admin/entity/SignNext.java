package com.itlozg.admin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.itlozg.admin.annotation.ExcelField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@TableName("sign_next")
public class SignNext extends TPBaseEntity implements Serializable {

    @TableField("user_id")
    private String userId;

    @TableField("sign_date")
    private Date signDate;

    @TableField("come_must_time")
    private String comeMustTime;

    @TableField("leave_must_time")
    private String leaveMustTime;

    @TableField("come_time")
    private String comeTime;

    @TableField("leave_time")
    private String leaveTime;

    @TableField("come_longitude")
    private String comeLongitude;

    @TableField("come_latitude")
    private String comeLatitude;

    @TableField("come_address")
    private String comeAddress;

    @TableField("come_addressname")
    private String comeAddressName;

    @TableField("come_remarks")
    private String comeRemarks;

    @TableField("leave_longitude")
    private String leaveLongitude;

    @TableField("leave_latitude")
    private String leaveLatitude;

    @TableField("leave_address")
    private String leaveAddress;

    @TableField("leave_addressname")
    private String leaveAddressName;

    @TableField("leave_remarks")
    private String leaveRemarks;

    /**
     * 打卡类型：0 固定；1 自由
     */
    @TableField("sign_type")
    private String signType;

    @TableField("tip_time")
    private String tipTime;

    @TableField("tip_msg")
    private String tipMsg;

    @TableField("is_must")
    private String isMust;

    @TableField("rule_id")
    private String ruleId;

    @TableField(exist = false)
    private String ruleName;

    @TableField(exist = false)
    private String locationClock;

    @TableField(exist = false)
    private String photographClock;

    @TableField("cross_day")
    private String crossDay;

    /**
     * 打卡方式：0 正常；1 自动
     */
    @TableField("sign_way")
    private String signWay;

    @TableField("sign_way_pm")
    private String signWayPm;

    @TableField("update_time_flag")
    private String updateTimeFlag;

    /**
     * rootval
     * 默认空着就行
     * android  root  1
     * ios          越狱   2
     */
    @TableField("root_val")
    private String rootVal;

    /**
     * 是否需要处理 默认不需要 1：需要
     */
    @TableField("need_check")
    private String needCheck;

    /**
     * 姓名
     */
    @TableField(exist = false)
    private String userName;

    /**
     * 部门
     */
    @TableField(exist = false)
    private String officeName;

    /**
     * 上午下班时间(打卡规则获取)
     */
    @TableField(exist = false)
    private String amOffTime;

    /**
     * 下午上班时间(打卡规则获取)
     */
    @TableField(exist = false)
    private String pmWorkTime;

    /**
     * 序号
     */
    @TableField(exist = false)
    private String order;

    /**
     * 导出打卡日期
     */
    @TableField(exist = false)
    private String exportSignDate;

    @TableField(exist = false)
    private String userNo;

    @TableField(exist = false)
    private String photo;

    @TableField(exist = false)
    private String handpunch;

    @TableField("group_id")
    private String groupId;

    @TableField("legwork_flag")
    private Integer legworkFlag;

    public Integer getLegworkFlag() {
        return legworkFlag;
    }

    public void setLegworkFlag(Integer legworkFlag) {
        this.legworkFlag = legworkFlag;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @TableField("sign_flag")
    private Integer signFlag;

    public Integer getSignFlag() {
        return signFlag;
    }

    public void setSignFlag(Integer signFlag) {
        this.signFlag = signFlag;
    }


    public String getHandpunch() {
        return handpunch;
    }

    public void setHandpunch(String handpunch) {
        this.handpunch = handpunch;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @ExcelField(title = "上班应打卡时间", sort = 7, align = 2)
    public String getComeMustTime() {
        return comeMustTime;
    }

    public void setComeMustTime(String comeMustTime) {
        this.comeMustTime = comeMustTime;
    }

    @ExcelField(title = "下班应打卡时间", sort = 10, align = 2)
    public String getLeaveMustTime() {
        return leaveMustTime;
    }

    public void setLeaveMustTime(String leaveMustTime) {
        this.leaveMustTime = leaveMustTime;
    }

    @ExcelField(title = "上班实际打卡时间", sort = 8, align = 2)
    public String getComeTime() {
        return comeTime;
    }

    public void setComeTime(String comeTime) {
        this.comeTime = comeTime;
    }

    @ExcelField(title = "下班实际打卡时间", sort = 11, align = 2)
    public String getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(String leaveTime) {
        this.leaveTime = leaveTime;
    }

    public String getComeLongitude() {
        return comeLongitude;
    }

    public void setComeLongitude(String comeLongitude) {
        this.comeLongitude = comeLongitude;
    }

    public String getComeLatitude() {
        return comeLatitude;
    }

    public void setComeLatitude(String comeLatitude) {
        this.comeLatitude = comeLatitude;
    }

    public String getLeaveLongitude() {
        return leaveLongitude;
    }

    public void setLeaveLongitude(String leaveLongitude) {
        this.leaveLongitude = leaveLongitude;
    }

    public String getLeaveLatitude() {
        return leaveLatitude;
    }

    public void setLeaveLatitude(String leaveLatitude) {
        this.leaveLatitude = leaveLatitude;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
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

    @ExcelField(title = "是否必须打卡", sort = 13, align = 2)
    public String getIsMust() {
        return isMust;
    }

    public void setIsMust(String isMust) {
        this.isMust = isMust;
    }

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    @ExcelField(title = "所属规则", sort = 6, align = 2)
    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public Date getSignDate() {
        return signDate;
    }

    public void setSignDate(Date signDate) {
        this.signDate = signDate;
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

    @ExcelField(title = "上班打卡地点", sort = 9, align = 2)
    public String getComeAddress() {
        return comeAddress;
    }

    public void setComeAddress(String comeAddress) {
        this.comeAddress = comeAddress;
    }

    @ExcelField(title = "下班打卡地点", sort = 12, align = 2)
    public String getLeaveAddress() {
        return leaveAddress;
    }

    public void setLeaveAddress(String leaveAddress) {
        this.leaveAddress = leaveAddress;
    }

    public String getSignWay() {
        return signWay;
    }

    public void setSignWay(String signWay) {
        this.signWay = signWay;
    }

    @ExcelField(title = "姓名", sort = 3, align = 2)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @ExcelField(title = "部门", sort = 5, align = 2)
    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getUpdateTimeFlag() {
        return updateTimeFlag;
    }

    public void setUpdateTimeFlag(String updateTimeFlag) {
        this.updateTimeFlag = updateTimeFlag;
    }

    public String getSignWayPm() {
        return signWayPm;
    }

    public void setSignWayPm(String signWayPm) {
        this.signWayPm = signWayPm;
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

    @ExcelField(title = "序号", sort = 1, align = 2)
    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    @ExcelField(title = "日期", sort = 2, align = 2)
    public String getExportSignDate() {
        return exportSignDate;
    }

    public void setExportSignDate(String exportSignDate) {
        this.exportSignDate = exportSignDate;
    }

    public String getRootVal() {
        return rootVal;
    }

    public void setRootVal(String rootVal) {
        this.rootVal = rootVal;
    }

    @ExcelField(title = "员工编号", sort = 4, align = 2)
    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getComeAddressName() {
        return comeAddressName;
    }

    public void setComeAddressName(String comeAddressName) {
        this.comeAddressName = comeAddressName;
    }

    public String getLeaveAddressName() {
        return leaveAddressName;
    }

    public void setLeaveAddressName(String leaveAddressName) {
        this.leaveAddressName = leaveAddressName;
    }

    public String getComeRemarks() {
        return comeRemarks;
    }

    public void setComeRemarks(String comeRemarks) {
        this.comeRemarks = comeRemarks;
    }

    public String getLeaveRemarks() {
        return leaveRemarks;
    }

    public void setLeaveRemarks(String leaveRemarks) {
        this.leaveRemarks = leaveRemarks;
    }

    public String getNeedCheck() {
        return needCheck;
    }

    public void setNeedCheck(String needCheck) {
        this.needCheck = needCheck;
    }

    @Override
    public String toString() {
        return "SignNext{" + "userId='" + userId + '\'' + ", signDate=" + signDate + ", comeMustTime='" + comeMustTime + '\'' + ", leaveMustTime='" + leaveMustTime + '\'' + ", comeTime='" + comeTime + '\'' + ", leaveTime='" + leaveTime + '\'' + ", comeLongitude='" + comeLongitude + '\'' + ", comeLatitude='" + comeLatitude + '\'' + ", comeAddress='" + comeAddress + '\'' + ", comeAddressName='" + comeAddressName + '\'' + ", comeRemarks='" + comeRemarks + '\'' + ", leaveLongitude='" + leaveLongitude + '\'' + ", leaveLatitude='" + leaveLatitude + '\'' + ", leaveAddress='" + leaveAddress + '\'' + ", leaveAddressName='" + leaveAddressName + '\'' + ", leaveRemarks='" + leaveRemarks + '\'' + ", signType='" + signType + '\'' + ", tipTime='" + tipTime + '\'' + ", tipMsg='" + tipMsg + '\'' + ", isMust='" + isMust + '\'' + ", ruleId='" + ruleId + '\'' + ", ruleName='" + ruleName + '\'' + ", locationClock='" + locationClock + '\'' + ", photographClock='" + photographClock + '\'' + ", crossDay='" + crossDay + '\'' + ", signWay='" + signWay + '\'' + ", signWayPm='" + signWayPm + '\'' + ", updateTimeFlag='" + updateTimeFlag + '\'' + ", rootVal='" + rootVal + '\'' + ", needCheck='" + needCheck + '\'' + ", userName='" + userName + '\'' + ", officeName='" + officeName + '\'' + ", amOffTime='" + amOffTime + '\'' + ", pmWorkTime='" + pmWorkTime + '\'' + ", order='" + order + '\'' + ", exportSignDate='" + exportSignDate + '\'' + ", userNo='" + userNo + '\'' + ", photo='" + photo + '\'' + ", handpunch='" + handpunch + '\'' + ", groupId=" + groupId + ", signFlag=" + signFlag + '}';
    }
}
