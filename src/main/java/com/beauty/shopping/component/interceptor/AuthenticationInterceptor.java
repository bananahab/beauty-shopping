package com.beauty.shopping.component.interceptor;

import com.beauty.shopping.common.api.ResultCode;
import com.beauty.shopping.common.exception.CommonException;
import com.beauty.shopping.entity.UserInfo;
import com.beauty.shopping.service.RedisService;
import com.beauty.shopping.service.UmsMemberService;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wuzhenxian
 * @date 2021/03/05
 * 登录拦截器
 */
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Resource
    private UmsMemberService umsMemberService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod method = (HandlerMethod) handler;
        //获取token
        String accessToken = getAuthToken(request);
        if (StringUtils.isBlank(accessToken)) {
            throw new CommonException(ResultCode.EMPTY_TOKEN);
        }
        //验证token
        String userId = umsMemberService.verifyToken(accessToken);
        if (userId == null) {
            throw new CommonException(ResultCode.INVALID_TOKEN);
        }

        request.setAttribute("userToken", accessToken);
        request.setAttribute("userId", userId);
        return true;
    }

    private String getAuthToken(HttpServletRequest request) {
        String token = request.getHeader("accessToken");
        if (token == null) {
            token = request.getParameter("accessToken");
        }
        return token;
    }

}
