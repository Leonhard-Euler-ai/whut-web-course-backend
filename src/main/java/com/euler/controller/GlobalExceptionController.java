package com.euler.controller;

import com.euler.domain.BaseResponse;
import com.euler.exception.IllegalRequestParamException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * 全局异常处理类，可细分具体异常
 * 返回统一格式
 * 暂时用控制台输出日志，后续可通过依赖生成日志文件
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/3/25
 */
@ControllerAdvice
public class GlobalExceptionController {
    /**
     * 请求参数异常处理，状态码400
     * IllegalRequestParamException 为自定义的参数异常，对应某个对象或字段Null或“”
     * HttpMessageNotReadableException为springboot抛出的异常，对应无法解析为想要的对象，可换为父类型
     */
    @ExceptionHandler({IllegalRequestParamException.class, HttpMessageNotReadableException.class})
    @ResponseBody
    public BaseResponse IllegalRequestParamExceptionHandler(Exception e){
        System.out.println(e);
        return new BaseResponse(HttpServletResponse.SC_BAD_REQUEST,
                e instanceof HttpMessageNotReadableException?"HttpMessageNotReadable":e.getMessage());
    }

    /**
     * 404异常，默认底层不会抛出，通过更改配置文件实现该异常的捕获
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody
    public BaseResponse noHandlerFound(NoHandlerFoundException e){
        System.out.println(e);
        return new BaseResponse(HttpServletResponse.SC_NOT_FOUND,Optional.ofNullable(e.getMessage()).orElse("请检查url是否正确"));
    }

    /**
     * 默认异常处理，状态码500
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public BaseResponse defaultExceptionHandler(Exception e){
        System.out.println(e);
        for(StackTraceElement stackTraceElement:e.getStackTrace()) {
            System.out.println(stackTraceElement);
        }
        return new BaseResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, Optional.ofNullable(e.getMessage()).orElse("服务器未知异常"));
    }
}

