package com.example.campus.interceptor;

import com.example.campus.annotation.RequireRole;
import com.example.campus.entity.User;
import com.example.campus.mapper.UserMapper;
import com.example.campus.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import com.example.campus.interceptor.RoleInterceptor;

@Component
public class RoleInterceptor implements HandlerInterceptor {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setContentType("application/json;charset=UTF-8");

        // 只处理方法级别的请求
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        RequireRole requireRole = handlerMethod.getMethod().getAnnotation(RequireRole.class);

        // 如果方法上没有 @RequireRole 注解，直接放行
        if (requireRole == null) {
            return true;
        }

        // 从请求头拿 Token
        String token = request.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            response.setStatus(401);
            response.getWriter().write("未登录");
            return false;
        }
        token = token.substring(7);

        // 从 Token 里提取用户名
        String username = JwtUtil.getUsernameFromToken(token);

        // 查用户表
        User user = userMapper.findByUsername(username);
        if (user == null || user.getRoleId() == null) {
            response.setStatus(403);
            response.getWriter().write("权限不足");
            return false;
        }

        // 判断角色是否匹配
        String requiredRole = requireRole.value();
        if ("ROLE_ADMIN".equals(requiredRole) && user.getRoleId() != 2) {
            response.setStatus(403);
            response.getWriter().write("需要管理员权限");
            return false;
        }

        return true;
    }
}