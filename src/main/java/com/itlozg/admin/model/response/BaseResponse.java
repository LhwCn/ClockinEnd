package com.itlozg.admin.model.response;

import java.util.Date;

public class BaseResponse {

    /**
     * ID
     */
    private String id;

    /**
     * 创建时间
     */
    protected Date creationDate;

    /**
     * 更新时间
     */
    protected Date lastUpdateDate;

    private String remarks;

    public BaseResponse() {
        super();
    }

    public BaseResponse(String id) {
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
