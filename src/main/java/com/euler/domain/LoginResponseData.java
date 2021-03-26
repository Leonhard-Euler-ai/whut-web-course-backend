package com.euler.domain;

import lombok.Data;

/**
 * 登录成功返回的数据实体
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/3/25
 */
@Data
public class LoginResponseData {
    private String nickname;
    private String mail;
    private String birthday;
    private String balance;
}
