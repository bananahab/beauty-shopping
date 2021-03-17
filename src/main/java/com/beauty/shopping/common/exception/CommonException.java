package com.beauty.shopping.common.exception;

import com.beauty.shopping.common.api.ResultCode;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 13955
 */
@Slf4j
public class CommonException extends Exception {
    public CommonException(ResultCode resultCode) {
        super(resultCode.getMessage());
        log.error(resultCode.getMessage());
    }
}
