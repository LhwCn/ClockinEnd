package com.itlozg.admin.model.request;



import java.util.Date;

public class SignNextRequest extends BaseRequest {

    /**
     * 用户id
     */
    private String userId;
    /**
     * 打卡日期
     */
    private Date signDate;
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
     * 上班打卡图片
     */
    private String comePhoto;

    /**
     * 下班打卡图片
     */
    private String leavePhoto;

    /**
     * 上下班打卡 0：上班打卡 1：下班打卡
     */
    private String commute;

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
     * 开始日期
     */
    private String startDate;

    /**
     * 结束日期
     */
    private String endDate;

    /**
     * 部门
     */
    private String officeId;

    /**
     * 姓名
     */
    private String userName;


    /**
     * 工号
     */
    private String userNo;

    /**
     * 参数值
     */
    private String paramVal;

    /**
     * rootVal
     * 默认空着就行
     * android  root  1
     * ios          越狱   2
     */
    private String rootVal;

    /**
     * 打卡设备编号
     */
    private String uniqueCode;

    /**
     * 是否前一天数据  1：为前一天数据
     */
    private String isLastDay;
    /**
     * 是否为更新打卡（自由打卡使用）
     */
    private String isUpdate;

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

    public String getCommute() {
        return commute;
    }

    public void setCommute(String commute) {
        this.commute = commute;
    }

    public String getCrossDay() {
        return crossDay;
    }

    public void setCrossDay(String crossDay) {
        this.crossDay = crossDay;
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getOfficeId() {
        return officeId;
    }

    public void setOfficeId(String officeId) {
        this.officeId = officeId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getParamVal() {
        return paramVal;
    }

    public void setParamVal(String paramVal) {
        this.paramVal = paramVal;
    }

    public String getRootVal() {
        return rootVal;
    }

    public void setRootVal(String rootVal) {
        this.rootVal = rootVal;
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

    public String getUniqueCode() {
        return uniqueCode;
    }

    public void setUniqueCode(String uniqueCode) {
        this.uniqueCode = uniqueCode;
    }

    public String getIsLastDay() {
        return isLastDay;
    }

    public void setIsLastDay(String isLastDay) {
        this.isLastDay = isLastDay;
    }

    public String getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(String isUpdate) {
        this.isUpdate = isUpdate;
    }

    @Override
    public String toString() {
        return "SignNextRequest{" +
                "userId='" + userId + '\'' +
                ", signDate=" + signDate +
                ", comeMustTime='" + comeMustTime + '\'' +
                ", leaveMustTime='" + leaveMustTime + '\'' +
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
                ", tipTime='" + tipTime + '\'' +
                ", tipMsg='" + tipMsg + '\'' +
                ", isMust='" + isMust + '\'' +
                ", ruleId='" + ruleId + '\'' +
                ", comePhoto='" + comePhoto + '\'' +
                ", leavePhoto='" + leavePhoto + '\'' +
                ", commute='" + commute + '\'' +
                ", crossDay='" + crossDay + '\'' +
                ", signWay='" + signWay + '\'' +
                ", signWayPm='" + signWayPm + '\'' +
                ", updateTimeFlag='" + updateTimeFlag + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", officeId='" + officeId + '\'' +
                ", userName='" + userName + '\'' +
                ", userNo='" + userNo + '\'' +
                ", paramVal='" + paramVal + '\'' +
                ", rootVal='" + rootVal + '\'' +
                ", uniqueCode='" + uniqueCode + '\'' +
                ", isLastDay='" + isLastDay + '\'' +
                ", isUpdate='" + isUpdate + '\'' +
                '}';
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    /**
     * 是否必须打卡
     */
    public static final String IS_MUST_YES = "0";//是
    public static final String IS_MUST_NO = "1";//否

    /**
     * 上下班打卡
     */
    public static final String SIGN_COME = "0";//上班
    public static final String SIGN_LEAVE = "1";//下班

    /**
     * 更新时间标识
     */
    public static final String UPDATE_TIME_YES = "0";//是
    public static final String UPDATE_TIME_NO = "1";//否

    /**
     * 上下班打卡方式
     */
    public static final String SIGN_WAY_NORMAL = "0";//正常打卡
    public static final String SIGN_WAY_SPEED = "1";//自动打卡

}
