package com.example.campus.config;

import com.example.campus.interceptor.JwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private JwtInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/api/**")          // 拦截所有 /api/ 开头的接口
                .excludePathPatterns(                // 放行以下路径（不需要登录也能访问）
                        "/api/students/register",    // 注册接口
                        "/api/students/login"        // 登录接口
                );
    }
}