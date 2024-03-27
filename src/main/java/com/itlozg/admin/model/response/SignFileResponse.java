package com.itlozg.admin.model.response;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class SignFileResponse extends BaseResponse {

    /**
     * code
     */
    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    /**
     * 用户id
     */
    private String userId;

    /**
     * 打卡日期
     */
    private Date signDate;

    /**
     * 状态0正常1缺卡2迟到3早退4位置异常5补卡
     */
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
     * 上午下班时间
     */
    private String amOffTime;

    /**
     * 下午上班时间
     */
    private String pmWorkTime;

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
    private String comeLongitude;

    /**
     * 上班打卡地点纬度
     */
    private String comeLatitude;
    /**
     * 下班打卡地址
     */
    private String comeAddress;
    /**
     * 上班备注
     */
    private String comeRemarks;

    /**
     * 上班打卡地址--名称
     */
    private String comeAddressName;

    /**
     * 下班打卡地点经度
     */
    private String leaveLongitude;

    /**
     * 下班打卡地点纬度
     */
    private String leaveLatitude;
    /**
     * 下班打卡地址
     */
    private String leaveAddress;

    /**
     * 下班打卡地址--名称
     */
    private String leaveAddressName;

    /**
     * 下班备注
     */
    private String leaveRemarks;

    /**
     * 打卡类型:0固定打卡 1自由
     */
    private String signType;

    /**
     * 是否必须打卡:0是1否
     */
    private String isMust;

    /**
     * 是否请假1是0否 2出差  3外勤
     */
    private String isLeave;

    /**
     * 请假类型
     */
    private String leaveType;

    /**
     * 打卡规则id
     */
    private String ruleId;

    /**
     * 是否需要处理 默认不需要 1：需要
     */
    private String needCheck;

    /**
     * 上班打卡图片
     */
    private String comePhoto;

    /**
     * 下班打卡图片
     */
    private String leavePhoto;

    /**
     * 打卡方式 0：正常打卡 1：自动打卡
     */
    private String signWay;

    /**
     * 更新时间标识 0：是 1：否
     */
    private String updateTimeFlag;

    /**
     * 姓名
     */
    private String userName;

    /**
     * 规则名
     */
    private String ruleName;

    /**
     * 部门
     */
    private String officeName;

    /**
     * 应该打卡次数
     */
    private String mustSignTimes;

    /**
     * 正常打卡次数
     */
    private String signTimes;
    /**
     * 区域补贴
     */
    private String subsidyCount;
    /**
     * 迟到次数
     */
    private String lateTimes;

    /**
     * 早退次数
     */
    private String earlyTimes;

    /**
     * 迟到早退次数
     */
    private String lateEarlyTimes;

    /**
     * 全天缺卡次数
     */
    private String absentTimes;

    /**
     * 工时
     */
    private BigDecimal workHours;

    /**
     * 实际工时(按照实际上班时间计算)
     */
    private BigDecimal workHoursReal;

    /**
     * 上午缺卡次数
     */
    private String amAbsentTimes;

    /**
     * 下午缺卡次数
     */
    private String pmAbsentTimes;

    /**
     * 打卡月份
     */
    private String signMonth;

    /**
     * 下班打卡方式 0：正常打卡 1：自动打卡
     */
    private String signWayPm;

    /**
     * 打卡状态
     */
    private String signStatus;

    /**
     * 打卡情况标识 0：正常 1：异常
     */
    private String situation;

    /**
     * 打卡明细
     */
    private List<SignNextAllResponse> details;

    /**
     * 外勤打卡明细
     */
    private List<SignLegworkResponse> legWorkDetails;

    /**
     * 正常打卡次数
     */
    private String normalCount;

    /**
     * 校准打卡次数
     */
    private String calibrationCount;

    /**
     * 异常打卡次数
     */
    private String exceptionCount;

    /**
     * 请假次数
     */
    private String leaveCount;

    /**
     * 请假次数--请假
     */
    private String leaveCountQj;

    /**
     * 请假次数--出差
     */
    private String leaveCountCc;

    /**
     * 请假次数--外出
     */
    private String leaveCountWc;

    /**
     * 机型
     */
    private String manufacturer;

    /**
     * 用户编号
     */
    private String userNo;

    /**
     * 是否四次打卡
     * 0：否
     * 1：是
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
     * 跨天打卡结束时间
     */
    private String endTime;

    /**
     * 平均值
     */
    private String average;

    /**
     * 工时总数
     */
    private String sumNum;

    /**
     * 用户数
     */
    private String userNum;

    /**
     * 导出打卡日期
     */
    private String exportSignDate;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getComeMustTime() {
        return comeMustTime;
    }

    public void setComeMustTime(String comeMustTime) {
        this.comeMustTime = comeMustTime;
    }

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

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

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

    public String getComePhoto() {
        return comePhoto;
    }

    public void setComePhoto(String comePhoto) {
        this.comePhoto = comePhoto;
    }

    public String getLeavePhoto() {
        return leavePhoto;
    }

    public void setLeavePhoto(String leavePhoto) {
        this.leavePhoto = leavePhoto;
    }

    public List<SignNextAllResponse> getDetails() {
        return details;
    }

    @Override
    public String toString() {
        return "SignFileResponse{" +
                "code=" + code +
                ", userId='" + userId + '\'' +
                ", signDate=" + signDate +
                ", status='" + status + '\'' +
                ", comeMustTime='" + comeMustTime + '\'' +
                ", leaveMustTime='" + leaveMustTime + '\'' +
                ", amOffTime='" + amOffTime + '\'' +
                ", pmWorkTime='" + pmWorkTime + '\'' +
                ", comeTime='" + comeTime + '\'' +
                ", leaveTime='" + leaveTime + '\'' +
                ", comeLongitude='" + comeLongitude + '\'' +
                ", comeLatitude='" + comeLatitude + '\'' +
                ", comeAddress='" + comeAddress + '\'' +
                ", comeRemarks='" + comeRemarks + '\'' +
                ", comeAddressName='" + comeAddressName + '\'' +
                ", leaveLongitude='" + leaveLongitude + '\'' +
                ", leaveLatitude='" + leaveLatitude + '\'' +
                ", leaveAddress='" + leaveAddress + '\'' +
                ", leaveAddressName='" + leaveAddressName + '\'' +
                ", leaveRemarks='" + leaveRemarks + '\'' +
                ", signType='" + signType + '\'' +
                ", isMust='" + isMust + '\'' +
                ", isLeave='" + isLeave + '\'' +
                ", leaveType='" + leaveType + '\'' +
                ", ruleId='" + ruleId + '\'' +
                ", needCheck='" + needCheck + '\'' +
                ", comePhoto='" + comePhoto + '\'' +
                ", leavePhoto='" + leavePhoto + '\'' +
                ", signWay='" + signWay + '\'' +
                ", updateTimeFlag='" + updateTimeFlag + '\'' +
                ", userName='" + userName + '\'' +
                ", ruleName='" + ruleName + '\'' +
                ", officeName='" + officeName + '\'' +
                ", mustSignTimes='" + mustSignTimes + '\'' +
                ", signTimes='" + signTimes + '\'' +
                ", subsidyCount='" + subsidyCount + '\'' +
                ", lateTimes='" + lateTimes + '\'' +
                ", earlyTimes='" + earlyTimes + '\'' +
                ", lateEarlyTimes='" + lateEarlyTimes + '\'' +
                ", absentTimes='" + absentTimes + '\'' +
                ", workHours=" + workHours +
                ", workHoursReal=" + workHoursReal +
                ", amAbsentTimes='" + amAbsentTimes + '\'' +
                ", pmAbsentTimes='" + pmAbsentTimes + '\'' +
                ", signMonth='" + signMonth + '\'' +
                ", signWayPm='" + signWayPm + '\'' +
                ", signStatus='" + signStatus + '\'' +
                ", situation='" + situation + '\'' +
                ", details=" + details +
                ", legWorkDetails=" + legWorkDetails +
                ", normalCount='" + normalCount + '\'' +
                ", calibrationCount='" + calibrationCount + '\'' +
                ", exceptionCount='" + exceptionCount + '\'' +
                ", leaveCount='" + leaveCount + '\'' +
                ", leaveCountQj='" + leaveCountQj + '\'' +
                ", leaveCountCc='" + leaveCountCc + '\'' +
                ", leaveCountWc='" + leaveCountWc + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", userNo='" + userNo + '\'' +
                ", isQuartic='" + isQuartic + '\'' +
                ", comeVeriface='" + comeVeriface + '\'' +
                ", leaveVeriface='" + leaveVeriface + '\'' +
                ", endTime='" + endTime + '\'' +
                ", average='" + average + '\'' +
                ", sumNum='" + sumNum + '\'' +
                ", userNum='" + userNum + '\'' +
                ", exportSignDate='" + exportSignDate + '\'' +
                '}';
    }

    public void setDetails(List<SignNextAllResponse> details) {
        this.details = details;
    }

    public List<SignLegworkResponse> getLegWorkDetails() {
        return legWorkDetails;
    }

    public void setLegWorkDetails(List<SignLegworkResponse> legWorkDetails) {
        this.legWorkDetails = legWorkDetails;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

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

    public BigDecimal getWorkHours() {
        return workHours;
    }

    public void setWorkHours(BigDecimal workHours) {
        this.workHours = workHours;
    }

    public BigDecimal getWorkHoursReal() {
        return workHoursReal;
    }

    public void setWorkHoursReal(BigDecimal workHoursReal) {
        this.workHoursReal = workHoursReal;
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

    public String getSignStatus() {
        return signStatus;
    }

    public void setSignStatus(String signStatus) {
        this.signStatus = signStatus;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public void setOrder(String toString) {
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

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getComeRemarks() {
        return comeRemarks;
    }

    public void setComeRemarks(String comeRemarks) {
        this.comeRemarks = comeRemarks;
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

    public String getLeaveRemarks() {
        return leaveRemarks;
    }

    public void setLeaveRemarks(String leaveRemarks) {
        this.leaveRemarks = leaveRemarks;
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

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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

    public String getExportSignDate() {
        return exportSignDate;
    }

    public void setExportSignDate(String exportSignDate) {
        this.exportSignDate = exportSignDate;
    }

    public String getNeedCheck() {
        return needCheck;
    }

    public void setNeedCheck(String needCheck) {
        this.needCheck = needCheck;
    }

    public String getSubsidyCount() {
        return subsidyCount;
    }

    public void setSubsidyCount(String subsidyCount) {
        this.subsidyCount = subsidyCount;
    }


}
