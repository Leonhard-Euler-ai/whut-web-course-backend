package com.euler.domain;

/**
 * 统一的响应格式
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/3/17
 */
public class BaseResponse<T> {
    /**
     * 状态码
     */
    private int code;

    /**
     * 响应消息
     */
    private String message;

    /**
     * 响应数据，可为空
     */
    private T data;

    public BaseResponse(int code,String message){
        this.code=code;
        this.message=message;
        this.data=null;
    }

    public BaseResponse(int code, String message, T data){
        this.code=code;
        this.message=message;
        this.data=data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}