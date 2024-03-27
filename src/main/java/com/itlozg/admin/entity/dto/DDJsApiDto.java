package com.itlozg.admin.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "钉钉鉴权实体类")
public class DDJsApiDto {
    @ApiModelProperty(value = "url")
    private String url;

    @ApiModelProperty(value = "sendBy码")
    private String sendBy;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSendBy() {
        return sendBy;
    }

    public void setSendBy(String sendBy) {
        this.sendBy = sendBy;
    }
}
