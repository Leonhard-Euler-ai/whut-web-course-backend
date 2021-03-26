package com.euler.interceptor;

import com.euler.domain.BaseResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Optional;

import static com.euler.constant.Constant.SESSION_KEY_USER;

/**
 * 登录请求拦截器
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/3/25
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if ( null==Optional.ofNullable(request.getSession(false))
                .map(session -> session.getAttribute(SESSION_KEY_USER)).orElse(null)) {
            response.setContentType("application/json;charset=UTF-8");
            ObjectMapper mapper = new ObjectMapper();
            response.getWriter().println(mapper.writeValueAsString(new BaseResponse<Void>(HttpServletResponse.SC_FORBIDDEN, "请先登录")));
            return false;
        }
        return true;
    }
}
