package com.euler.service;

import com.euler.domain.BaseResponse;

/**
 * 注册服务接口类
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/4/6
 */
public interface RegisterService {
    /**
     * 注册时发送手机验证码
     *
     * @param mobile 手机号
     * @return
     */
    BaseResponse sendPhoneVerCode(String mobile);


    /**
     * 注册
     *
     * @param username 手机号
     * @param password 密码
     * @param userName 用户真实姓名
     * @param verCode  验证码
     * @return
     */
    BaseResponse register(String username, String password, String userName, String verCode);
}
