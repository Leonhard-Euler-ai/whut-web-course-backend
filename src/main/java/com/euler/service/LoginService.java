package com.euler.service;

import com.euler.domain.BaseResponse;
import com.euler.domain.User;

import javax.servlet.http.HttpSession;

/**
 * 登录服务类接口
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/3/25
 */
public interface LoginService {

    /**
     * 登录处理
     *
     * @param user 登录用户对象
     * @param httpSession
     * @return
     */
    BaseResponse login(User user, HttpSession httpSession);
}
