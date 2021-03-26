package com.euler.interceptor;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * 跨域过滤器
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/3/25
 */
@WebFilter("/**")
public class CorsFilter implements Filter {
    public static final String HTTP_METHOD_OPTIONS="OPTIONS";
    List<String> DEFAULT_SUPPORTED_REQUEST_HEADERS = Arrays.asList(
            "Host",
            "User-Agent",
            "Origin",
            "Referer",
            "Connection",
            "Cache-Control",
            "X-Requested-With",
            "Content-Type",
            "Accept",
            "Accept-Language",
            "Accept-Encoding",
            "Access-Control-Request-Method",
            "Access-Control-Request-Header",
            "token",
            "Pragma"
    );

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        if (!(request instanceof HttpServletRequest) || !(response instanceof HttpServletResponse)) {
            throw new ServletException("不是HTTP请求");
        }
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-Control-Allow-Origin", Optional.ofNullable(httpServletRequest.getHeader("origin")).orElse("*"));
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS");
        httpServletResponse.setHeader("Access-Control-Expose-Headers","token");
        List<String> allowHeaders = new LinkedList<>(DEFAULT_SUPPORTED_REQUEST_HEADERS);
        Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            allowHeaders.add(headerNames.nextElement());
        }
        httpServletResponse.setHeader("Access-Control-Allow-Headers", String.join(",", allowHeaders));
        if (HTTP_METHOD_OPTIONS.equalsIgnoreCase(httpServletRequest.getMethod())) {
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            return;
        }
        chain.doFilter(request, response);
    }
}
