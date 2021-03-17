package com.beauty.shopping.dao;

import com.beauty.shopping.entity.UserInfo;
import org.apache.ibatis.annotations.Param;

/**
 * @author wuzhenxian
 * @date 2021/03/15
 * 用户信息
 */
public interface UserInfoMapper {

    /**
     * 获取用户信息
     * @param userName 用户名
     * @param password 密码
     * @return 用户信息
     */
    UserInfo getUserInfo(@Param("userName") String userName, @Param("password") String password);

    /**
     * 通过用户名查询账户信息
     * @param userName 用户名
     * @return 用户信息
     */
    UserInfo getUserInfoByUserName(@Param("userName") String userName);

    /**
     * 添加新用户
     * @param userName 用户名
     * @param password 密码
     * @return 添加结果
     */
    boolean addUser(@Param("userName") String userName, @Param("password") String password);
}
