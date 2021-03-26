package com.euler.service;

import com.euler.domain.ManageUserInfoResponseData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 类描述
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/3/25
 */
@SpringBootTest
class UserServiceImplTest {
    @Autowired
    private UserServiceImpl userService;

    @Test
    void contextLoads() {
        // 测试删除用户
//        System.out.println(userService.deleteUserByUserName("test").getData());
        // 测试修改用户信息
        ManageUserInfoResponseData userInfo=new ManageUserInfoResponseData();
        userInfo.setName("李航飞");
        // 密码暂时不让改
        userInfo.setPassword("");
        userInfo.setMail("qq");
        userInfo.setBirthday("1010");
        userInfo.setBalance("325");
        System.out.println(userService.updateUserInfo(userInfo));
    }

}