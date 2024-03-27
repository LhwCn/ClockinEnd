package com.itlozg.admin.entity.dto;

import lombok.Data;

/**
 * 接收前端登录请求的参数
 */
@Data
public class UserDTO {

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 真实姓名
     */
    private String realname;
}
