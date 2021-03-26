package com.euler.domain;

import lombok.Data;

/**
 * 用户管理表中的数据实体
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/3/25
 */
@Data
public class ManageUserInfoResponseData {
    /**
     * name对应数据库user_info表中user_name字段
     */
    private String name;
    private String password;
    private String mail;
    private String birthday;
    private String balance;
}
