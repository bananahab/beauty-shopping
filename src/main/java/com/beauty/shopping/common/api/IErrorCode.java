package com.beauty.shopping.common.api;

import io.swagger.models.auth.In;
/**
 * 封装API的错误码
 * @author wuzhenxian
 * @date 2021/03/03
 */
public interface IErrorCode {
    long getCode();

    String getMessage();
}