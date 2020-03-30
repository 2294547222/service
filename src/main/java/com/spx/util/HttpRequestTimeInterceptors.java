package com.spx.util;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Http请求时间统计
 * 拦截所有请求
 */
public class HttpRequestTimeInterceptors extends HandlerInterceptorAdapter {

    ThreadLocal<Long> localThread = new ThreadLocal<Long>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //线程方式
        localThread.set(System.currentTimeMillis());
        //request方式
        request.setAttribute("_startTime", System.currentTimeMillis());

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        Long endTime = System.currentTimeMillis();


        System.out.println(request.getServletPath() + " >> http请求结束线程：" + (endTime - localThread.get()));



        Long startTime = (Long)request.getAttribute("_startTime");

        System.out.println(request.getServletPath() + " >> http请求结束:" + (endTime - startTime));

    }
}