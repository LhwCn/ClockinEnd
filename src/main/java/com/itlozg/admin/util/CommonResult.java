package com.itlozg.admin.util;

import lombok.Data;
import org.springframework.validation.BindingResult;

/**
 * 通用返回对象
 * Created by zscat on 2018/4/26.
 */
@Data
public class CommonResult {

    /**
     * 操作成功
     */
    private static final int SUCCESS = 200;
    /**
     * 操作失败
     */
    private static final int FAILED = 500;
    /**
     * 参数校验失败
     */
    private static final int VALIDATE_FAILED = 201;
    /**
     * 未认证
     */
    private static final int UNAUTHORIZED = 401;
    /**
     * 未授权
     */
    private static final int FORBIDDEN = 403;


    private Integer code;
    /**
     * 0:失败 1:成功
     */
    private Integer success;

    private String message;
    private String logMessage;
    private Object data;

    /**
     * 普通成功返回
     *
     * @param data 获取的数据
     */
    public static CommonResult success(Object data) {
        return new CommonResult(SUCCESS, "操作成功", data, 1);
    }


    /**
     * 普通成功返回
     */
    public static CommonResult success() {
        return new CommonResult(SUCCESS, "操作成功", null, 1);
    }

    /**
     * 日志成功返回
     */
    public static CommonResult logSuccess(String logMessage) {
        return new CommonResult(SUCCESS, "操作成功", logMessage, null);
    }

    /**
     * 日志成功返回
     *
     * @param data 获取的数据
     */
    public static CommonResult logSuccess(Object data, String logMessage) {
        return new CommonResult(SUCCESS, "操作成功", logMessage, data, 1);
    }

    /**
     * 普通成功返回
     */
    public static CommonResult success(String msg, Object data) {
        return new CommonResult(SUCCESS, msg, data, 1);
    }

    /**
     * 操作失败
     */
    public static CommonResult failed(int code) {
        if (code == 100) {
            return new CommonResult(code, "请先登录", null, 0);
        }
        return new CommonResult(code, null, null, 0);
    }

    /*
    * 操作失败，返回失败信息
    * */
    public static CommonResult failed(String message) {
        return new CommonResult(FAILED, message, null, 0);
    }

    /**
     * 普通成功返回
     */
    public static CommonResult success(Integer code, String message) {
        return new CommonResult(code, message, null, 1);
    }

    /**
     * 普通成功返回
     */
    public static CommonResult success(Integer code, String message, Object data) {
        return new CommonResult(code, message, data, 1);
    }

    /**
     * 普通失败提示信息
     */
    public static CommonResult failed() {
        return new CommonResult(FAILED, "操作失败", null, 0);
    }

    /**
     * 操作失败
     */
    public static CommonResult failed(String message, Object data) {
        return new CommonResult(FAILED, message, data, 0);
    }

    /**
     * 操作失败
     */
    public static CommonResult failed(Object data) {
        return new CommonResult(FAILED, "操作失败", data, 0);
    }


    /**
     * 操作失败
     */
    public static CommonResult failed(Integer code, String message) {
        return new CommonResult(code, message, null, 0);
    }

    /**
     * 操作失败
     *
     * @param code    状态码
     * @param message 返回消息
     * @param data    返回数据
     */
    public static CommonResult failed(Integer code, String message, Object data) {
        return new CommonResult(code, message, data, 0);
    }


    /**
     * 参数验证失败使用
     *
     * @param message 错误信息
     */
    public static CommonResult validateFailed(String message) {
        return new CommonResult(VALIDATE_FAILED, message, null, 0);
    }

    /**
     * 未登录时使用
     *
     * @param message 错误信息
     */
    public static CommonResult unauthorized(String message) {
        return new CommonResult(UNAUTHORIZED, "暂未登录或token已经过期", null, 0);
    }

    /**
     * 未授权时使用
     *
     * @param message 错误信息
     */
    public static CommonResult forbidden(String message) {
        return new CommonResult(FORBIDDEN, "没有相关权限", null, 0);
    }

    /**
     * 参数验证失败使用
     *
     * @param result 错误信息
     */
    public static CommonResult validateFailed(BindingResult result) {
        try {
            return validateFailed(result.getFieldError().getDefaultMessage());
        } catch (NullPointerException e) {
            return failed();
        }
    }

    /**
     * 普通失败提示信息
     */
    public static CommonResult paramFailed() {
        return new CommonResult(FAILED, "参数失败", null, 0);
    }

    public static CommonResult paramFailed(String message) {
        return new CommonResult(FAILED, message, null, 0);
    }

    public CommonResult() {
    }

    public CommonResult(Integer code, String message, Object data, Integer success) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.success = success;
    }

    public CommonResult(Integer code, String message, String logMessage, Object data, Integer success) {
        this.code = code;
        this.message = message;
        this.logMessage = logMessage;
        this.data = data;
        this.success = success;
    }

}
