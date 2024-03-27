package com.itlozg.admin.model.request;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class BaseRequest extends PageRequest {

    /**
     * ID
     */
    private String id;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected Date creationDate;

    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected Date lastUpdateDate;

    /**
     * 是否已删除 1=已删除 0=未删除
     */
    private Integer isDeleted;

    private String remarks;

    public BaseRequest() {
        super();
    }

    public BaseRequest(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * 删除标记（0：正常；1：删除；）
     */
    public static final String DEL_FLAG_NORMAL = "0";
    public static final String DEL_FLAG_DELETE = "1";
    public static final Integer DEL_FLAG_DEL = 1;
    public static final Integer DEL_FLAG_NOR = 0;

}
