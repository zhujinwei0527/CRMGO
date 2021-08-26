package com.example.crmgo.register.utils.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author nantian
 * @description 拦截墙
 */

@Slf4j
@Service
public class Intercept implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        log.info("测试拦截器启动");
        String url = request.getRequestURL().toString();
        log.info(url);
        Object sessionid = request.getSession().getAttribute("customerId");
        if (null == sessionid) {
            response.sendError(500, "请先登录");
            return false;
        }
        log.info("sessionid={}", sessionid);
        return true;
    }
}
