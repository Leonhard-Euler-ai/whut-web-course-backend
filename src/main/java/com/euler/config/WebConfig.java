package com.euler.config;

import com.euler.interceptor.CorsFilter;
import com.euler.interceptor.LoginInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web配置类
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/3/25
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册登录拦截器
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/login", "/error","/register","/api/sendVerCode");
    }

    /**
     * 注册跨域拦截器bean组件
     */
    @Bean
    public FilterRegistrationBean corsFilter(){
        return new FilterRegistrationBean(new CorsFilter());
    }
}
