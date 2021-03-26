package com.euler.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 用户信息实体类
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/3/25
 */
@Entity
@Data
public class UserInfo {
    @Id
    private Integer userId;
    /**
     * 对应表中的user_name，用户真实姓名
     */
    private String userName;
    private String nickname;
    private String mail;
    private String birthday;
    private String balance;
}
