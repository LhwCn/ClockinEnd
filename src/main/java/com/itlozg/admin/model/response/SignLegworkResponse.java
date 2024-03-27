package com.itlozg.admin.model.response;



public class SignLegworkResponse extends BaseResponse {

    /**
     * 用户id
     */
    private String userId;

    /**
     * 打卡时间
     */
    private String signTime;

    /**
     * 打卡地点经度
     */
    private String signLongitude;

    /**
     * 打卡地点纬度
     */
    private String signLatitude;

    /**
     * 打卡图片
     */
    private String signPhoto;

    private String userName;

    private String userNo;

    private String officeName;

    /**
     * 外勤打卡地址
     */
    private String clockInSite;

    private Integer flag;

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    /**
     * rootVal
     * 默认空着就行
     * android  root  1
     * ios          越狱   2
     */
    private String rootVal;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSignTime() {
        return signTime;
    }

    public void setSignTime(String signTime) {
        this.signTime = signTime;
    }

    public String getSignLongitude() {
        return signLongitude;
    }

    public void setSignLongitude(String signLongitude) {
        this.signLongitude = signLongitude;
    }

    public String getSignLatitude() {
        return signLatitude;
    }

    public void setSignLatitude(String signLatitude) {
        this.signLatitude = signLatitude;
    }

    public String getSignPhoto() {
        return signPhoto;
    }

    public void setSignPhoto(String signPhoto) {
        this.signPhoto = signPhoto;
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

    public String getClockInSite() {
        return clockInSite;
    }

    public void setClockInSite(String clockInSite) {
        this.clockInSite = clockInSite;
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
}
