package com.euler.repository;

import com.euler.domain.User;
import com.euler.domain.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 类描述
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/3/25
 */
@SpringBootTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    DataSource dataSource;

    @Test
    void contextLoads() {
//        // 获取数据源
//        System.out.println(dataSource.getClass());

//        // 获取用户信息
//        System.out.println(userRepository.findUserByUsernameAndPassword("李航飞", "123456"));
//        System.out.println(userInfoRepository.findById(1).get());

        //删除用户
        //userInfoRepository.deleteById(3);

       // System.out.println(userRepository.findUserByUsername("13720113769"));

//        System.out.println(userInfoRepository.findUserByUserName("测试"));

//        //添加用户信息
//        UserInfo userInfo = new UserInfo();
//        userInfo.setUserId(3);
//        userInfo.setUserName("匿名");
//        userInfo.setNickname("昵称");
//        //userInfo.setMail("qq");
//        //userInfo.setBirthday("10");
//        //userInfo.setBalance("2000");
//        System.out.println(userInfoRepository.saveAndFlush(userInfo));

//        // 注册用户,自增主键自增长
//        User user=new User();
//        user.setUsername("龚东东");
//        user.setPassword("123456");
//        System.out.println(userRepository.saveAndFlush(user));

        //通过saveAndFlush更新
//        UserInfo userInfo= userInfoRepository.findById(12).orElse(null);
//       if(null==userInfo){
//           System.out.println("userInfo为空");
//           return;
//       }
//        userInfo.setNickname("验证");
//        userInfo.setMail("mail");
//        userInfo.setBirthday("2020-10-12");
//        System.out.println(userInfoRepository.saveAndFlush(userInfo));

        //
    }
}