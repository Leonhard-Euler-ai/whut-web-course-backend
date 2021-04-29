package com.euler.service;

import com.euler.domain.BaseResponse;
import com.euler.domain.User;
import com.euler.domain.UserInfo;
import com.euler.repository.UserInfoRepository;
import com.euler.repository.UserRepository;
import com.euler.utils.RedisUtil;
import com.euler.utils.SmsUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 类描述
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/4/6
 */
@Service
public class RegisterServiceImpl implements RegisterService {
    final UserRepository userRepository;
    final UserInfoRepository userInfoRepository;
    final RedisUtil redisUtil;

    public RegisterServiceImpl(UserRepository userRepository, UserInfoRepository userInfoRepository, RedisUtil redisUtil) {
        this.userRepository = userRepository;
        this.userInfoRepository = userInfoRepository;
        this.redisUtil = redisUtil;
    }

    @Override
    public BaseResponse sendPhoneVerCode(String mobile) {
        String verCode = SmsUtil.getPhoneVerCode();
        SmsUtil.SendSms(mobile, "您的注册验证码为" + verCode + ",3分钟内有效");
        redisUtil.set(mobile, verCode, (long) 180);
        return new BaseResponse(HttpServletResponse.SC_OK, "验证码发送成功", verCode);
    }

    @Override
    public BaseResponse register(String username, String password, String userName, String verCode) {
        if (ObjectUtils.isEmpty(redisUtil.get(username))) {
            return new BaseResponse(HttpServletResponse.SC_BAD_REQUEST, "验证码已失效");
        } else if (!redisUtil.get(username).equals(verCode)) {
            return new BaseResponse(HttpServletResponse.SC_BAD_REQUEST, "验证码错误");
        }

        List<UserInfo> userInfoList = userInfoRepository.findUserByUserName(username);
        if (userInfoList.isEmpty()) {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            Integer userId = userRepository.saveAndFlush(user).getId();

            //添加用户信息
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(userId);
            userInfo.setUserName(userName);
            userInfo.setNickname("昵称");
            userInfo.setBalance("0");
            System.out.println(userInfoRepository.saveAndFlush(userInfo));

            return new BaseResponse(HttpServletResponse.SC_OK, "注册成功");
        }
        return new BaseResponse(HttpServletResponse.SC_BAD_REQUEST, "该手机号已注册");
    }
}
