package com.beauty.shopping.service.impl;

import com.beauty.shopping.common.api.CommonResult;
import com.beauty.shopping.dao.ProductCategoryMapper;
import com.beauty.shopping.entity.ProductCategoryDO;
import com.beauty.shopping.service.ProductCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wuzhenxian
 * @date 2021/03/22
 * 商品分类
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Resource
    private ProductCategoryMapper productCategoryMapper;

    @Override
    public CommonResult getAllProductCategory(Integer adminId) {
        List<ProductCategoryDO> productCategoryDOList = productCategoryMapper.getAllByAdminId(adminId);
        return CommonResult.success(productCategoryDOList);
    }

    @Override
    public CommonResult delByIdAndAdminId(Integer id, Integer adminId) {
        productCategoryMapper.delByAdminIdAndId(id, adminId);
        return CommonResult.success(null);
    }

    @Override
    public CommonResult updateByProductCategory(ProductCategoryDO productCategoryDO) {
        productCategoryMapper.updateByProductCategory(productCategoryDO);
        return CommonResult.success(null);
    }

    @Override
    public CommonResult addSelective(ProductCategoryDO productCategoryDO) {
        productCategoryMapper.addSelective(productCategoryDO);
        return CommonResult.success(productCategoryDO);
    }
}
