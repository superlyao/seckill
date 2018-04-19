package com.yliao.seckill.config;


import io.sentry.Sentry;

import javax.servlet.*;
import java.io.IOException;

/**
 * @Author: yliao
 * @Date: Created in 2018/4/5
 */
public class SentryFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    }

    public void destroy() {

    }
}
