package com.beauty.shopping.common.api;

import com.beauty.shopping.common.api.IErrorCode;

/**
 * 枚举了一些常用API操作码
 * @author wuzhenxian
 * @date 2021/03/03
 */
public enum ResultCode implements IErrorCode {
    /**
     * 操作成功
     */
    SUCCESS(200, "操作成功"),
    /**
     * 操作失败
     */
    FAILED(500, "操作失败"),

    /**
     * 参数验证失败
     */
    VALIDATE_FAILED(404, "参数检验失败"),

    UNAUTHORIZED(401, "暂未登录或token已经过期"),

    FORBIDDEN(403, "没有相关权限"),

    LOGIN_FAIL(406, "登录失败"),

    EMPTY_TOKEN(301, "没有Token"),

    INVALID_TOKEN(302, "无效的Token");
    private long code;
    private String message;

    private ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}