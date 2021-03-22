package com.beauty.shopping.controller;

import com.beauty.shopping.common.api.CommonResult;
import com.beauty.shopping.entity.ProductCategoryDO;
import com.beauty.shopping.service.ProductCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author wuzhenxian
 * @date 2021/03/22
 * 商品Controller
 */
@Api(tags = "productController")
@Controller
@RequestMapping("/product")
@Slf4j
@CrossOrigin
public class ProductController {
    @Resource
    private ProductCategoryService productCategoryService;

    @PostMapping(value = "/getAllProductCategory")
    @ApiOperation("获取所有的分类")
    @ResponseBody
    public CommonResult getAllProductCategory() {
        Integer adminId = 1;
        return productCategoryService.getAllProductCategory(adminId);
    }

    @PostMapping(value = "/updateProductCategory")
    @ApiOperation("编辑分类")
    @ResponseBody
    public CommonResult updateProductCategory(@RequestBody ProductCategoryDO productCategoryDO) {
        productCategoryDO.setAdminId(1);
        return productCategoryService.updateByProductCategory(productCategoryDO);
    }

    @PostMapping(value = "/delProductCategory")
    @ApiOperation("删除分类")
    @ResponseBody
    public CommonResult delProductCategory(@RequestParam("id") Integer id) {
        Integer adminId = 1;
        return productCategoryService.delByIdAndAdminId(id, adminId);
    }

    @PostMapping(value = "/addProductCategory")
    @ApiOperation("新增分类")
    @ResponseBody
    public CommonResult addProductCategory(@RequestBody ProductCategoryDO productCategoryDO) {
        productCategoryDO.setAdminId(1);
        return productCategoryService.addSelective(productCategoryDO);
    }
}
