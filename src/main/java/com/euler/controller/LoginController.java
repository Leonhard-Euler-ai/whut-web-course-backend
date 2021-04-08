package com.euler.controller;

import com.euler.domain.BaseResponse;
import com.euler.domain.User;
import com.euler.service.LoginService;
import com.euler.service.RegisterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


/**
 * 登录控制器
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/3/25
 */
@Controller
public class LoginController {
    final LoginService loginService;
    final RegisterService registerService;

    public LoginController(LoginService loginService, RegisterService registerService) {
        this.loginService = loginService;
        this.registerService = registerService;
    }

    @PostMapping("/login")
    @ResponseBody
    public BaseResponse login(@RequestBody User user, HttpSession httpSession) {
        return loginService.login(user, httpSession);
    }

    @PostMapping("/api/sendVerCode")
    @ResponseBody
    public BaseResponse sendVerCode(@RequestParam String mobile) {
        return registerService.sendPhoneVerCode(mobile);
    }

    @PostMapping("/register")
    @ResponseBody
    public BaseResponse register(@RequestParam String username,
                                 @RequestParam String mobile,
                                 @RequestParam String password,
                                 @RequestParam String verCode) {
        return registerService.register(mobile,password,username,verCode);
    }
}