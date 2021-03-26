package com.euler.repository;

import com.euler.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 用户表数据库访问类
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/3/25
 */
public interface UserRepository extends JpaRepository<User,Integer> {
    /**
     * 根据用户登录名从数据库中查找用户
     * @param username 用户登录名
     * @return 用户列表
     */
    List<User> findUserByUsername(String username);


    /**
     * 根据用户登录名和密码从数据库中查找用户
     * @param username 用户登录名
     * @param password 密码
     * @return 用户列表
     */
    List<User> findUserByUsernameAndPassword(String username, String password);
}
