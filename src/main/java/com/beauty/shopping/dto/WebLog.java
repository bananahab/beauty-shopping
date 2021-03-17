package com.beauty.shopping.dto;

import io.swagger.annotations.ApiOperation;
import lombok.*;

/**
 * @author wuzhenxian
 * @date 2021/03/03
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WebLog {
    private String description;

    private Long startTime;

    private Integer spendTime;

    private String method;

    private Object parameter;

    private Object result;

    private String url;

}
