package com.example.crmgo.register.utils.interConfig;

import com.example.crmgo.register.utils.interceptor.Intercept;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author nantian
 * @description 拦截器配置
 */
@Configuration
public class InterceptConfig implements WebMvcConfigurer {
    @Resource
    private Intercept intercept;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(intercept)
                .addPathPatterns("/**")
                .excludePathPatterns("/user/register")
                .excludePathPatterns("/user/login")
//                .excludePathPatterns("/user/logout")
                .excludePathPatterns("/error")
                .excludePathPatterns("/roleManage/query")
                .excludePathPatterns("/roleManage/add")
                .excludePathPatterns("/roleManage/modify")
//                .excludePathPatterns("/userRoleQueryAll")
                .excludePathPatterns("/test1")
                .excludePathPatterns("/test2")
                .excludePathPatterns("/test3");
    }
}
