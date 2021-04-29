package com.euler.repository;

import com.euler.domain.User;
import com.euler.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 用户表数据库访问类
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/3/25
 */
public interface UserInfoRepository extends JpaRepository<UserInfo,Integer> {
    /**
     * 根据用户真实姓名查找用户
     * @param username 用户真实姓名
     * @return
     */
    List<UserInfo> findUserByUserName(String username);

    @Override
    <S extends UserInfo> S save(S entity);
}