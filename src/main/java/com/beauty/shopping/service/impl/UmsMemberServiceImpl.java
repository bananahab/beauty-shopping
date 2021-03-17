package com.beauty.shopping.service.impl;

import com.beauty.shopping.common.api.CommonResult;
import com.beauty.shopping.common.api.ResultCode;
import com.beauty.shopping.common.exception.CommonException;
import com.beauty.shopping.common.utils.TokenUtil;
import com.beauty.shopping.dao.UserInfoMapper;
import com.beauty.shopping.entity.UserInfo;
import com.beauty.shopping.service.RedisService;
import com.beauty.shopping.service.UmsMemberService;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Random;

/**
 * @author wuzhenxian
 * @date 2021/03/03
 */
@Slf4j
@Service
public class UmsMemberServiceImpl implements UmsMemberService {
    @Resource
    private RedisService redisService;
    @Resource
    private UserInfoMapper userInfoMapper;
    @Value("${redis.key.prefix.authCode}")
    private String redisPrefixAuthCode;
    @Value("${redis.key.expire.authCode}")
    private Long redisAuthExpireTime;
    @Value("${redis.key.prefix.tokenCache}")
    private String redisPrefixTokenCache;
    @Value("${redis.key.expire.tokenCache}")
    private Long redisExpireTokenCache;
    @Value("${redis.token.expire}")
    private Long redisTokenExpireTime;
    @Value("${token.secret}")
    private String tokenSecret;
    @Value("${token.ttlMillis}")
    private Long ttlMillis;
    @Value("${token.issuer}")
    private String issuer;

    @Override
    public CommonResult generateAuthCode(String telephone) {
        StringBuilder authCode = new StringBuilder();
        //验证码为四位
        int num = 4;
        for (int i = 0; i < num; i++) {
            authCode.append(String.valueOf(new Random().nextInt(10)));
        }
        //手机号码不能为空，这个需要前端进行校验
        redisService.set(redisPrefixAuthCode + telephone, authCode.toString());
        redisService.expire(redisPrefixAuthCode + telephone, redisAuthExpireTime);
        log.info("redis缓存验证码：{}, 缓存的key值为:{}", authCode.toString(), redisPrefixAuthCode + telephone);
        return CommonResult.success(authCode.toString(), "获取验证码成功");
    }

    @Override
    public CommonResult verifyAuthCode(String telephone, String authCode) {
        String existAuthCode = redisService.get(redisPrefixAuthCode + telephone);
        if (authCode.equals(existAuthCode)) {
            return CommonResult.success(null, "验证码校验成功");
        }
        return CommonResult.failed("验证码不正确");

    }

    @Override
    public String verifyToken(String token) {
        Claims claims = TokenUtil.parseJWT(token, tokenSecret);
        if (claims == null) {
            return null;
        }
        log.info("用户:{}登录成功", claims.getId());
        return claims.getId();
    }

    @Override
    public CommonResult getToken(String userName, String password) {
        UserInfo userInfo = userInfoMapper.getUserInfo(userName, password);
        if (userInfo != null) {
            String token = TokenUtil.createJwtToken(String.valueOf(userInfo.getId()), issuer, ttlMillis, tokenSecret);
            return CommonResult.success(token);
        }
        return new CommonResult(ResultCode.LOGIN_FAIL.getCode(), "用户名或者密码错误");
    }

    @Override
    public CommonResult addUser(String userName, String password) {
        userInfoMapper.addUser(userName, password);
        return CommonResult.success(null, "用户注册成功");
    }


}
