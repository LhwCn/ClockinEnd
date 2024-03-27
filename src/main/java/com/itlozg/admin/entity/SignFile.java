package com.itlozg.admin.entity;



import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.itlozg.admin.annotation.ExcelField;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("sign_file")
public class SignFile extends TPBaseEntity implements Serializable {



    /**
     * 用户id
     */
    @TableField("user_id")
    private String userId;

    /**
     * 用户OA
     */
    @TableField(exist = false)
    private String userOa;

    /**
     * 部门名称
     */
    @TableField(exist = false)
    private String deptname;


    /**
     * 打卡日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date signDate;

    /**
     * 状态
     */
    @TableField("status")
    private String status;

    /**
     * 上班应打卡时间
     */
    private String comeMustTime;

    /**
     * 下班应打卡时间
     */
    private String leaveMustTime;

    /**
     * 实际上班打卡时间
     */
    private String comeTime;

    /**
     * 实际下班打卡时间
     */
    private String leaveTime;

    /**
     * 上班打卡地点经度
     */
    @TableField("come_longitude")
    private String comeLongitude;

    /**
     * 上班打卡地点纬度
     */
    @TableField("come_latitude")
    private String comeLatitude;
    /**
     * 上班打卡地址
     */
    @TableField("come_address")
    private String comeAddress;

    /**
     * 上班打卡地址--名称
     */
    @TableField("come_addressname")
    private String comeAddressName;
    /**
     * 上班打卡备注
     */
    @TableField("come_remarks")
    private String comeRemarks;
    /**
     * 下班打卡地点经度
     */
    @TableField("leave_longitude")
    private String leaveLongitude;

    /**
     * 下班打卡地点纬度
     */
    @TableField("leave_latitude")
    private String leaveLatitude;
    /**
     * 下班打卡地址
     */
    @TableField("leave_address")
    private String leaveAddress;
    /**
     * 下班打卡地址
     */
    @TableField("leave_addressname")
    private String leaveAddressName;
    /**
     * 下班打卡备注
     */
    @TableField("leave_remarks")
    private String leaveRemarks;
    /**
     * 打卡类型:0固定时间上下班1自由上下班
     */
    @TableField("sign_type")
    private String signType;

    /**
     * 是否必须打卡:0是1否
     */
    @TableField("is_must")
    private String isMust;

    /**
     * 是否请假1是0否 2出差  3外勤 4调休
     */
    @TableField("is_leave")
    private String isLeave;

    /**
     * 请假类型0病假1事假2婚假3丧假4产假5年休假6法定休假7公假期（出参加选举）
     */
    @TableField("leave_type")
    private String leaveType;

    /**
     * 打卡规则id
     */
    @TableField("rule_id")
    private String ruleId;



    /**
     * 跨天 0：不跨天 1：跨天
     */
    @TableField("cross_day")
    private String crossDay;

    /**
     * 工时
     */
    @TableField("work_hours")
    private BigDecimal workHours;

    /**
     * 实际工时(按照实际上班时间计算)
     */
    @TableField("work_hours_real")
    private BigDecimal workHoursReal;

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
     * 更新时间标识 0：是 1：否
     */
    @TableField("update_time_flag")
    private String updateTimeFlag;

    /**
     * 打卡状态
     */
    @TableField("sign_status")
    private String signStatus;

    /**
     * 打卡情况标识 0：正常 1：异常
     */
    private String situation;

    /**
     * 是否需要处理 默认不需要 1：需要
     */
    @TableField("need_check")
    private String needCheck;

    /**
     * 姓名
     */
    @TableField("user_name")
    private String userName;

    /**
     * 规则名
     */
    @TableField(exist = false)
    private String ruleName;

    /**
     * 部门
     */
    @TableField(exist = false)
    private String officeName;

    /**
     * 应该打卡次数
     */
    @TableField(exist = false)
    private String mustSignTimes;

    /**
     * 正常打卡次数
     */
    @TableField(exist = false)
    private String signTimes;

    /**
     * 迟到次数
     */
    @TableField(exist = false)
    private String lateTimes;

    /**
     * 早退次数
     */
    @TableField(exist = false)
    private String earlyTimes;

    /**
     * 迟到早退次数
     */
    @TableField(exist = false)
    private String lateEarlyTimes;

    /**
     * 全天缺卡次数
     */
    @TableField(exist = false)
    private String absentTimes;

    /**
     * 上午缺卡次数
     */
    @TableField(exist = false)
    private String amAbsentTimes;

    /**
     * 下午缺卡次数
     */
    @TableField(exist = false)
    private String pmAbsentTimes;

    /**
     * 打卡月份
     */
    @TableField(exist = false)
    private String signMonth;

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

    /**
     * 正常打卡次数
     */
    @TableField(exist = false)
    private String normalCount;

    /**
     * 校准打卡次数
     */
    @TableField(exist = false)
    private String calibrationCount;

    /**
     * 异常打卡次数
     */
    @TableField(exist = false)
    private String exceptionCount;
    /**
     * 区域补贴
     */
    @TableField(exist = false)
    private String subsidyCount;
    /**
     * 请假次数
     */
    @TableField(exist = false)
    private String leaveCount;

    /**
     * 请假次数--请假
     */
    @TableField(exist = false)
    private String leaveCountQj;

    /**
     * 请假次数--出差
     */
    @TableField(exist = false)
    private String leaveCountCc;

    /**
     * 请假次数--外出
     */
    @TableField(exist = false)
    private String leaveCountWc;

    /**
     * 机型
     */
    @TableField(exist = false)
    private String manufacturer;

    /**
     * 用户编号
     */
    @TableField(exist = false)
    private String userNo;
    /**
     * 平均值
     */
    @TableField(exist = false)
    private String average;
    /**
     * 工时总数
     */
    @TableField(exist = false)
    private String sumNum;
    /**
     * 用户数
     */
    @TableField(exist = false)
    private String userNum;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    @ExcelField(title = "上班打卡", sort = 7, align = 2)
    public String getComeMustTime() {
        return comeMustTime;
    }

    public void setComeMustTime(String comeMustTime) {
        this.comeMustTime = comeMustTime;
    }

    @ExcelField(title = "下班打卡", sort = 9, align = 2)
    public String getLeaveMustTime() {
        return leaveMustTime;
    }

    public void setLeaveMustTime(String leaveMustTime) {
        this.leaveMustTime = leaveMustTime;
    }

    public String getComeTime() {
        return comeTime;
    }

    public void setComeTime(String comeTime) {
        this.comeTime = comeTime;
    }

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

    @ExcelField(title = "上班打卡地点", sort = 8, align = 2)
    public String getComeAddressName() {
        return comeAddressName;
    }

    public void setComeAddressName(String comeAddressName) {
        this.comeAddressName = comeAddressName;
    }

    @ExcelField(title = "上班打卡备注", sort = 17, align = 2)
    public String getComeRemarks() {
        return comeRemarks;
    }

    public void setComeRemarks(String comeRemarks) {
        this.comeRemarks = comeRemarks;
    }

    @ExcelField(title = "下班打卡地点", sort = 10, align = 2)
    public String getLeaveAddressName() {
        return leaveAddressName;
    }

    public void setLeaveAddressName(String leaveAddressName) {
        this.leaveAddressName = leaveAddressName;
    }

    @ExcelField(title = "下班打卡备注", sort = 17, align = 2)
    public String getLeaveRemarks() {
        return leaveRemarks;
    }

    public void setLeaveRemarks(String leaveRemarks) {
        this.leaveRemarks = leaveRemarks;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    @ExcelField(title = "必须打卡", sort = 10, align = 2)
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

    public Date getSignDate() {
        return signDate;
    }

    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @ExcelField(title = "校准来源", sort = 16, align = 2)
    public String getIsLeave() {
        return isLeave;
    }

    public void setIsLeave(String isLeave) {
        this.isLeave = isLeave;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }


    public String getCrossDay() {
        return crossDay;
    }

    public void setCrossDay(String crossDay) {
        this.crossDay = crossDay;
    }

    @ExcelField(title = "打卡时长（小时）", sort = 12, align = 2)
    public BigDecimal getWorkHours() {
        return workHours;
    }

    public void setWorkHours(BigDecimal workHours) {
        this.workHours = workHours;
    }

    @ExcelField(title = "校准工作时长（小时）", sort = 13, align = 2)
    public BigDecimal getWorkHoursReal() {
        return workHoursReal;
    }

    public void setWorkHoursReal(BigDecimal workHoursReal) {
        this.workHoursReal = workHoursReal;
    }

    public String getComeAddress() {
        return comeAddress;
    }

    public void setComeAddress(String comeAddress) {
        this.comeAddress = comeAddress;
    }

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

    @ExcelField(title = "所属规则", sort = 6, align = 2)
    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    @ExcelField(title = "部门", sort = 5, align = 2)
    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getMustSignTimes() {
        return mustSignTimes;
    }

    public void setMustSignTimes(String mustSignTimes) {
        this.mustSignTimes = mustSignTimes;
    }

    public String getSignTimes() {
        return signTimes;
    }

    public void setSignTimes(String signTimes) {
        this.signTimes = signTimes;
    }

    public String getSignMonth() {
        return signMonth;
    }

    public void setSignMonth(String signMonth) {
        this.signMonth = signMonth;
    }

    public String getLateTimes() {
        return lateTimes;
    }

    public void setLateTimes(String lateTimes) {
        this.lateTimes = lateTimes;
    }

    public String getEarlyTimes() {
        return earlyTimes;
    }

    public void setEarlyTimes(String earlyTimes) {
        this.earlyTimes = earlyTimes;
    }

    public String getLateEarlyTimes() {
        return lateEarlyTimes;
    }

    public void setLateEarlyTimes(String lateEarlyTimes) {
        this.lateEarlyTimes = lateEarlyTimes;
    }

    public String getAbsentTimes() {
        return absentTimes;
    }

    public void setAbsentTimes(String absentTimes) {
        this.absentTimes = absentTimes;
    }

    public String getAmAbsentTimes() {
        return amAbsentTimes;
    }

    public void setAmAbsentTimes(String amAbsentTimes) {
        this.amAbsentTimes = amAbsentTimes;
    }

    public String getPmAbsentTimes() {
        return pmAbsentTimes;
    }

    public void setPmAbsentTimes(String pmAbsentTimes) {
        this.pmAbsentTimes = pmAbsentTimes;
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

    @ExcelField(title = "状态", sort = 14, align = 2)
    public String getSignStatus() {
        return signStatus;
    }

    public void setSignStatus(String signStatus) {
        this.signStatus = signStatus;
    }

    @ExcelField(title = "校准", sort = 15, align = 2)
    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
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

    public String getNormalCount() {
        return normalCount;
    }

    public void setNormalCount(String normalCount) {
        this.normalCount = normalCount;
    }

    public String getCalibrationCount() {
        return calibrationCount;
    }

    public void setCalibrationCount(String calibrationCount) {
        this.calibrationCount = calibrationCount;
    }

    public String getExceptionCount() {
        return exceptionCount;
    }

    public void setExceptionCount(String exceptionCount) {
        this.exceptionCount = exceptionCount;
    }

    public String getLeaveCount() {
        return leaveCount;
    }

    public void setLeaveCount(String leaveCount) {
        this.leaveCount = leaveCount;
    }

    public String getLeaveCountQj() {
        return leaveCountQj;
    }

    public void setLeaveCountQj(String leaveCountQj) {
        this.leaveCountQj = leaveCountQj;
    }

    public String getLeaveCountCc() {
        return leaveCountCc;
    }

    public void setLeaveCountCc(String leaveCountCc) {
        this.leaveCountCc = leaveCountCc;
    }

    public String getLeaveCountWc() {
        return leaveCountWc;
    }

    public void setLeaveCountWc(String leaveCountWc) {
        this.leaveCountWc = leaveCountWc;
    }

    @ExcelField(title = "打卡设备", sort = 11, align = 2)
    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @ExcelField(title = "用户编号", sort = 4, align = 2)
    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
    }

    public String getSumNum() {
        return sumNum;
    }

    public void setSumNum(String sumNum) {
        this.sumNum = sumNum;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getNeedCheck() {
        return needCheck;
    }

    public void setNeedCheck(String needCheck) {
        this.needCheck = needCheck;
    }

    public String getUserOa() {
        return userOa;
    }

    public void setUserOa(String userOa) {
        this.userOa = userOa;
    }

    public String getSubsidyCount() {
        return subsidyCount;
    }

    public void setSubsidyCount(String subsidyCount) {
        this.subsidyCount = subsidyCount;
    }
}
