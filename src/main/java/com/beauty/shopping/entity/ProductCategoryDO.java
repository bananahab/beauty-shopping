package com.beauty.shopping.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author wuzhenxian
 * @date 2021/03/22
 * 商品分类
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategoryDO {
    private Integer id;

    private String name;

    private Integer showStatus;

    private Integer adminId;

    private String keywords;

    private String description;

    private Date createTime;

    private Date modifyTime;
}
