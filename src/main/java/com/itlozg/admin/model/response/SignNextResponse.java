package com.itlozg.admin.model.response;

import java.util.Date;
import java.util.List;

public class SignNextResponse extends BaseResponse {

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
     * 打卡日期  （字符串）
     */
    private String signDateStr;

    /**
     * 上班时间
     */
    private String comeMustTime;

    /**
     * 下班时间
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
    private String comeLongitude;

    /**
     * 上班打卡地点纬度
     */
    private String comeLatitude;

    /**
     * 上班打卡地址
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
     * 规则类型0固定时间上下班1自由上下班
     */
    private String signType;

    /**
     * 打卡提醒时间
     */
    private String tipTime;

    /**
     * 打卡提醒内容
     */
    private String tipMsg;

    /**
     * 是否必须打卡:0是1否
     */
    private String isMust;

    /**
     * 打卡规则id
     */
    private String ruleId;

    /**
     * 打卡规则名称
     */
    private String ruleName;

    /**
     * 上班打卡图片
     */
    private String comePhoto;

    /**
     * 下班打卡图片
     */
    private String leavePhoto;

    /**
     * 位置打卡：0启用1禁用
     */
    private String locationClock;

    /**
     * 拍照打卡：0启用1停用
     */
    private String photographClock;

    /**
     * 跨天 0：不跨天 1：跨天
     */
    private String crossDay;

    /**
     * 打卡方式 0：正常打卡 1：自动打卡
     */
    private String signWay;

    /**
     * 下班打卡方式 0：正常打卡 1：自动打卡
     */
    private String signWayPm;

    /**
     * 更新时间标识 0：是 1：否
     */
    private String updateTimeFlag;

    /**
     * 姓名
     */
    private String userName;

    /**
     * 部门
     */
    private String officeName;

    /**
     * 上午下班时间(打卡规则获取)
     */
    private String amOffTime;

    /**
     * 下午上班时间(打卡规则获取)
     */
    private String pmWorkTime;

    /**
     * 是否需要处理 默认不需要 1：需要
     */
    private String needCheck;

    /**
     * rootVal
     * 默认空着就行
     * android  root  1
     * ios          越狱   2
     */
    private String rootVal;

    /**
     * 打卡明细
     */
    private List<SignNextAllResponse> details;

    /**
     * 员工编号
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
     * 限制手动打卡
     */
    private String handpunch;

    /**
     * 考勤组id
     */
    private String groupId;

    /**
     * 是否外勤打卡标志
     */
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

    public String getHandpunch() {
        return handpunch;
    }

    public void setHandpunch(String handpunch) {
        this.handpunch = handpunch;
    }

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

    public String getSignDateStr() {
        return signDateStr;
    }

    public void setSignDateStr(String signDateStr) {
        this.signDateStr = signDateStr;
    }

    public List<SignNextAllResponse> getDetails() {
        return details;
    }

    public void setDetails(List<SignNextAllResponse> details) {
        this.details = details;
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

    public String getRootVal() {
        return rootVal;
    }

    public void setRootVal(String rootVal) {
        this.rootVal = rootVal;
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

    public String getNeedCheck() {
        return needCheck;
    }

    public void setNeedCheck(String needCheck) {
        this.needCheck = needCheck;
    }
}

