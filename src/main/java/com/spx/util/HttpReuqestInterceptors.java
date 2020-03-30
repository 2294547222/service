package com.spx.util;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpReuqestInterceptors extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestUri = request.getRequestURI();
        String contextPath = request.getContextPath();
        if (requestUri.equals("/login/LoginAdmin")) {
            return true;
        } else {
            if (request.getSession().getAttribute("token") != null) {
                return true;
            } else {
                return false;
            }
        }
    }
}
