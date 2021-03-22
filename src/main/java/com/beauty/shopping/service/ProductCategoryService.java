package com.beauty.shopping.service;

import com.beauty.shopping.common.api.CommonResult;
import com.beauty.shopping.entity.ProductCategoryDO;
import io.swagger.models.auth.In;

/**
 * @author wuzhenxian
 * @date 2021/03/22
 * 商品分类
 */
public interface ProductCategoryService {
    /**
     * 获取所有分类
     * @param adminId 商家id
     * @return 结果
     */
    CommonResult getAllProductCategory(Integer adminId);

    /**
     * 删除分类
     * @param id 分类id
     * @param adminId 商家id
     * @return 结果
     */
    CommonResult delByIdAndAdminId(Integer id, Integer adminId);

    /**
     * 编辑分类
     * @param productCategoryDO 商品分类
     * @return 结果
     */
    CommonResult updateByProductCategory(ProductCategoryDO productCategoryDO);

    /**
     * 新增分类
     * @param productCategoryDO 商品分类
     * @return 结果
     */
    CommonResult addSelective(ProductCategoryDO productCategoryDO);
}
