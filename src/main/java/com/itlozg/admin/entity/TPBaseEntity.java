package com.itlozg.admin.entity;

import com.baomidou.mybatisplus.annotation.TableField;

import java.util.Date;
import java.util.UUID;

public class TPBaseEntity extends PK {
    /**
     * 创建时间
     */
    @TableField("CREATION_DATE")
    protected Date creationDate;
    /**
     * 更新时间
     */
    @TableField("LAST_UPDATE_DATE")
    protected Date lastUpdateDate;
    /**
     * 是否已删除 1=已删除 0=未删除
     */
    @TableField("DEL_FLAG")
    private Integer delFlag;
    /**
     * 备注信息
     */
    @TableField("REMARKS")
    private String remarks;
    /**
     * 创建者
     */
    @TableField("CREATED_BY")
    private String createdBy;
    /**
     * 更新者
     */
    @TableField("LAST_UPDATED_BY")
    private String lastUpdatedBy;

    public TPBaseEntity() {
        super();
    }

    public TPBaseEntity(String id) {
        super(id);
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
}
