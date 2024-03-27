package com.itlozg.admin.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "单点登录表单")
public class DDSSOLogin {
    @ApiModelProperty(value = "code码")
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSendBy() {
        return sendBy;
    }

    public void setSendBy(String sendBy) {
        this.sendBy = sendBy;
    }

    @ApiModelProperty(value = "sendBy码")
    private String sendBy;
}
