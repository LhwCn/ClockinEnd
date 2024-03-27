package com.itlozg.admin.entity;



import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.itlozg.admin.annotation.ExcelField;

import java.io.Serializable;

@TableName("SIGN_LEGWORK")
public class SignLegwork extends TPBaseEntity implements Serializable {


    /**
     * 用户id
     */
    @TableField("user_id")
    private String userId;

    /**
     * 打卡时间
     */
    @TableField("sign_time")
    private String signTime;

    /**
     * 打卡地点经度
     */
    @TableField("sign_longitude")
    private String signLongitude;

    /**
     * 打卡地点纬度
     */
    @TableField("sign_latitude")
    private String signLatitude;

    /**
     * 外勤打卡地址
     */
    @TableField("clock_in_site")
    private String clockInSite;

    /**
     * 打卡图片
     */
    @TableField("sign_photo")
    private String signPhoto;

    /**
     * rootval
     * 默认空着就行
     * android  root  1
     * ios          越狱   2
     */
    @TableField("root_val")
    private String rootVal;

    /**
     * 打卡设备编号
     */
    @TableField("unique_code")
    private String uniqueCode;

    /**
     * 序号
     */
    @TableField(exist = false)
    private String order;

    @TableField(exist = false)
    private String userName;

    @TableField(exist = false)
    private String userNo;

    @TableField(exist = false)
    private String officeName;

    @TableField("group_id")
    private String groupId;
    /**
     * 打卡日期
     */
    protected String signLegworkDate;




    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @ExcelField(title = "打卡时间", sort = 5, align = 2)
    public String getSignTime() {
        return signTime;
    }

    public void setSignTime(String signTime) {
        this.signTime = signTime;
    }

    @ExcelField(title = "打卡地点经度", sort = 6, align = 2)
    public String getSignLongitude() {
        return signLongitude;
    }

    public void setSignLongitude(String signLongitude) {
        this.signLongitude = signLongitude;
    }

    @ExcelField(title = "打卡地点维度", sort = 7, align = 2)
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

    @ExcelField(title = "用户名", sort = 2, align = 2)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @ExcelField(title = "工号", sort = 2, align = 2)
    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    @ExcelField(title = "所属组织", sort = 3, align = 2)
    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    @ExcelField(title = "外勤地址", sort = 8, align = 2)
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

    @ExcelField(title = "序号", sort = 1, align = 2)
    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    @ExcelField(title = "打卡日期", sort = 4, align = 2)
    public String getSignLegworkDate() {
        return signLegworkDate;
    }

    public void setSignLegworkDate(String signLegworkDate) {
        this.signLegworkDate = signLegworkDate;
    }

    @TableField(exist = false)
    private String remarksInfo;

    @ExcelField(title = "备注信息", sort = 9, align = 2)
    public String getRemarksInfo() {
        return remarksInfo;
    }

    public void setRemarksInfo(String remarksInfo) {
        this.remarksInfo = remarksInfo;
    }

    public String getUniqueCode() {
        return uniqueCode;
    }

    public void setUniqueCode(String uniqueCode) {
        this.uniqueCode = uniqueCode;
    }
}
