package com.example.campus.interceptor;

import com.example.campus.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setContentType("application/json;charset=UTF-8");
        // 1. 从请求头里拿 Token
        String token = request.getHeader("Authorization");

        // 2. 没带 Token，直接拒绝
        if (token == null || !token.startsWith("Bearer ")) {
            response.setStatus(401);
            response.getWriter().write("未登录，请先登录");
            return false;
        }

        // 3. 去掉 "Bearer " 前缀，拿到纯 Token
        token = token.substring(7);

        // 4. 验证 Token 是否有效
        if (!JwtUtil.validateToken(token)) {
            response.setStatus(401);
            response.getWriter().write("Token无效或已过期，请重新登录");
            return false;
        }

        // 5. 验证通过，放行
        return true;
    }
}