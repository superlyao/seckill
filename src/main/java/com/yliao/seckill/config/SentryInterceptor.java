package com.yliao.seckill.config;



import io.sentry.Sentry;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: yliao
 * @Date: Created in 2018/4/5
 */
public class SentryInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String id = request.getSession().getId();
        String reqPath = request.getPathInfo();
        Sentry.getContext().addTag("sessionId", id);
        Sentry.getContext().addTag("reqPath", reqPath);
        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
