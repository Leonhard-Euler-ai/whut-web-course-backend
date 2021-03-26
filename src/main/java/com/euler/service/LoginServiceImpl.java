package com.euler.service;

import com.euler.domain.BaseResponse;
import com.euler.domain.LoginResponseData;
import com.euler.domain.User;
import com.euler.domain.UserInfo;
import com.euler.exception.IllegalRequestParamException;
import com.euler.repository.UserInfoRepository;
import com.euler.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.List;
import java.util.Optional;

import static com.euler.constant.Constant.SESSION_KEY_USER;

/**
 * 登录服务实现类
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/3/25
 */
@Service
public class LoginServiceImpl implements LoginService{
    final UserRepository userRepository;
    final UserInfoRepository userInfoRepository;

    public LoginServiceImpl(UserRepository userRepository, UserInfoRepository userInfoRepository) {
        this.userRepository = userRepository;
        this.userInfoRepository = userInfoRepository;
    }

    @Override
    public BaseResponse login(User user, HttpSession httpSession) {
        if (null == user || Optional.ofNullable(user.getUsername()).orElse("").isEmpty()
                || Optional.ofNullable(user.getPassword()).orElse("").isEmpty()) {
            throw new IllegalRequestParamException("用户登录参数缺失");
        }

        List<User> userList = userRepository.findUserByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (!userList.isEmpty()) {
            // 根据用户id获取用户信息
            UserInfo userInfo = userInfoRepository.findById(userList.get(0).getId()).get();
            //设置session
            httpSession.setAttribute(SESSION_KEY_USER, userInfo.getUserId());
            // 设置返回体中的data
            LoginResponseData data = new LoginResponseData();
            data.setNickname(userInfo.getNickname());
            data.setMail(userInfo.getMail());
            data.setBirthday(userInfo.getBirthday());
            data.setBalance(userInfo.getBalance());

            return new BaseResponse(HttpServletResponse.SC_OK, "登录成功", data);
        }

        // 状态码403
        return new BaseResponse(HttpServletResponse.SC_FORBIDDEN, "用户名或密码错误");
    }
}
