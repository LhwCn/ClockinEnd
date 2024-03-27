package com.itlozg.admin.entity.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 登录表单
 *
 * @author Mark sunlightcs@gmail.com
 * @since 3.1.0 2018-01-25
 */
@TableName("tb_user")
@ApiModel(value = "登录表单")
public class LoginDto {
    @ApiModelProperty(value = "员工号")
    private String id;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "员工姓名")
    private String username;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

