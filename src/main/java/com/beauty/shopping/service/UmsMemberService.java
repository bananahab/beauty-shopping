package com.beauty.shopping.service;

import com.beauty.shopping.common.api.CommonResult;

/**
 * @author wuzhenxian
 * @date 2021
 */
public interface UmsMemberService {

    /**
     * 获取验证码,并将验证码缓存在redis中
     * @param telephone 电话号码
     * @return 结果
     */
    CommonResult generateAuthCode(String telephone);

    /**
     * 验证验证码是否正确
     * @param telephone 电话号码
     * @param authCode 验证码
     * @return 结果
     */
    CommonResult verifyAuthCode(String telephone, String authCode);

    /**
     * 验证token
     * @param token
     * @return
     */
    String verifyToken(String token);

    /**
     * 返回token
     * @param userName
     * @param password
     * @return
     */
    CommonResult getToken(String userName, String password);

    /**
     * 注册用户信息
     * @param userName 用户名
     * @param password 用户密码
     * @return 注册结果
     */
    CommonResult addUser(String userName, String password);
}
