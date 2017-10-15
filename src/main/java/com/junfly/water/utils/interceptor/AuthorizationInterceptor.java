package com.junfly.water.utils.interceptor;

import com.junfly.water.config.Global;
import com.junfly.water.entity.sys.SysToken;
import com.junfly.water.service.sys.SysTokenService;
import com.junfly.water.utils.RRException;
import com.junfly.water.utils.annotation.IgnoreAuth;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限(Token)验证
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-23 15:38
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private SysTokenService sysTokenService;

    public static final String LOGIN_USER_KEY = "LOGIN_USER_KEY";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        IgnoreAuth annotation;
        if(handler instanceof HandlerMethod) {
            annotation = ((HandlerMethod) handler).getMethodAnnotation(IgnoreAuth.class);
        }else{
            return true;
        }

        //如果有@IgnoreAuth注解，则不验证token
        if(annotation != null){
            return true;
        }

        //从header中获取token
        String token = "";
        String authorization = request.getHeader("Authorization");
        if(StringUtils.isNotEmpty(authorization)){
            String[] authorizationArray = authorization.split(" ");
            if (authorizationArray.length > 1) {
                token = authorizationArray[1];
            }
        }

        //如果header中不存在token，则从参数中获取token
        if(StringUtils.isBlank(token)){
            token = request.getParameter("token");
        }

        //token为空
        if(StringUtils.isBlank(token)){
            throw new RRException("token不能为空", HttpServletResponse.SC_UNAUTHORIZED);
        }

        //查询token信息
        SysToken tokenEntity = sysTokenService.queryByToken(token);
        if(tokenEntity == null || tokenEntity.getExpireTime().getTime() < System.currentTimeMillis()){
            throw new RRException("token失效，请重新登录", HttpServletResponse.SC_UNAUTHORIZED);
        }

        //设置userId到request里，后续根据userId，获取用户信息
        request.setAttribute(Global.LOGIN_USER_KEY, tokenEntity.getUserId());

        return true;
    }
}
