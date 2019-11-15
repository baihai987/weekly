package com.hyy.kcbweekly.weekly.config;

import com.hyy.kcbweekly.weekly.anno.NoLogin;
import com.hyy.kcbweekly.weekly.anno.Token;
import com.hyy.kcbweekly.weekly.entity.TokenCode;
import com.hyy.kcbweekly.weekly.mapper.TokeCodeMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class AuthenticationInterceptor implements HandlerInterceptor {
    @Resource
    private TokeCodeMapper tokeCodeMapper;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
//        //设置跨域--开始
//        HttpServletResponse httpResponse = (HttpServletResponse) response;
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        if (httpRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
//            setHeader(httpRequest, httpResponse);
//            return true;
//        }
//        //设置跨域--结束
        NoLogin noLogin = ((HandlerMethod) handler).getMethodAnnotation(NoLogin.class);
        if (noLogin.required()) {
            return true;
        }

        Token annotation;
        if (handler instanceof HandlerMethod) {
            annotation = ((HandlerMethod) handler).getMethodAnnotation(Token.class);
        } else {
            return true;
        }

        if (annotation == null) {
            return true;
        }

        String token = request.getHeader("token");
        //如果header中不存在token，则从参数中获取token
        if (StringUtils.isBlank(token)) {
            token = request.getParameter("token");
        }

        if (StringUtils.isBlank(token)) {
            throw new RuntimeException();
        }

        //查询token信息
        TokenCode tokenCode = tokeCodeMapper.findToken(token);

        if (tokenCode == null) {
            throw new RuntimeException();
        }
        tokenCode.setCreate_time(new Date());
        tokeCodeMapper.update(tokenCode);
        //设置userId到request里，后续根据userId，获取用户信息

        return true;
    }

    /**
     * 为response设置header，实现跨域
     */
    private void setHeader(HttpServletRequest request, HttpServletResponse response) {
        //跨域的header设置
        response.setHeader("Access-control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", request.getMethod());
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
        //防止乱码，适用于传输JSON数据
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
    }
}
