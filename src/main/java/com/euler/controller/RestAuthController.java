package com.euler.controller;

import com.alibaba.fastjson.JSONObject;
import com.euler.domain.BaseResponse;
import com.euler.domain.LoginResponseData;
import com.xkcoding.http.support.Http;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.request.AuthGithubRequest;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static com.euler.constant.Constant.SESSION_KEY_USER;

/**
 * 类描述
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/4/8
 */
@RestController
@RequestMapping("/oauth")
public class RestAuthController {

    /**
     * 返回前端github登录跳转链接
     */
    @RequestMapping("/github")
    public BaseResponse renderAuth(HttpServletResponse response) throws IOException {
        AuthRequest authRequest = getAuthRequest();
        String redirectUrl = authRequest.authorize(AuthStateUtils.createState());
        return new BaseResponse(HttpServletResponse.SC_OK, "获取跳转链接成功", redirectUrl);
    }

    /**
     * 验证是否登录成功，成功时返回给前端用户信息
     *
     * @param callback 登录成功返回的code,state等信息构成的对象
     * @return
     * @throws IOException
     */
    @RequestMapping("/github/authInfo")
    public BaseResponse login(AuthCallback callback, HttpSession httpSession) throws IOException {
        AuthRequest authRequest = getAuthRequest();
        AuthResponse authResponse = authRequest.login(callback);

        // 将响应的数据格式化为json字符串
        String dataString = JSONObject.toJSONString(authResponse.getData());
        // 将字符串转化为json对象
        JSONObject authData = JSONObject.parseObject(dataString);

        // 获取用户信息发回给前端
        LoginResponseData githubData = new LoginResponseData();
        githubData.setNickname(authData.getString("username"));
        githubData.setMail("");
        githubData.setBirthday("");
        githubData.setBalance("");

        //设置session
        httpSession.setAttribute(SESSION_KEY_USER, 1);
        return new BaseResponse(HttpServletResponse.SC_OK, "github登录成功", githubData);
    }

    private AuthRequest getAuthRequest() {
        return new AuthGithubRequest(AuthConfig.builder()
                .clientId("32e9e1d6a2bf0f6141d9")
                .clientSecret("53299c56b25dce940a1d1fa9d4ca2277d2d7a6f9")
                .redirectUri("http://127.0.0.1:8080/oauth/github/callback")
                .build());
    }
}