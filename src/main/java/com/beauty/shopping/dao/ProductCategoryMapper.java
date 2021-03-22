package com.beauty.shopping.dao;

import com.beauty.shopping.entity.ProductCategoryDO;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wuzhenxian
 * @date 2021/03/22
 * 商品分类
 */
public interface ProductCategoryMapper {

    /**
     * 查询所有的分类
     * @param adminId 商家id
     * @return 分类
     */
    List<ProductCategoryDO> getAllByAdminId(@Param("adminId") Integer adminId);

    /**
     * 删除分类
     * @param Id 分类id
     * @param adminId 商家id
     */
    void delByAdminIdAndId(@Param("id") Integer Id,@Param("adminId") Integer adminId);

    /**
     * 编辑分类
     * @param productCategoryDO 商品分类
     */
    void updateByProductCategory(ProductCategoryDO productCategoryDO);

    /**
     * 新增分类
     * @param productCategoryDO 商品分类
     */
    void addSelective(ProductCategoryDO productCategoryDO);
}
