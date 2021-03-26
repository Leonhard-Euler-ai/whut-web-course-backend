package com.euler.exception;

/**
 * 请求参数有误异常
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/3/25
 */
public class IllegalRequestParamException extends IllegalArgumentException {
    public IllegalRequestParamException(String message) {
        super(message);
    }
}
