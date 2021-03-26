package com.euler.service;

import com.euler.domain.BaseResponse;
import com.euler.domain.ManageUserInfoResponseData;

/**
 * 用户service接口
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/3/25
 */
public interface UserService {
    /**
     * 获取所有用户信息
     *
     * @return http返回体，其中的data为所有用户信息的列表
     */
    BaseResponse getAllUserInfo();

    /**
     * 获取除了该用户ID外其他所有的用户信息
     * @param id 用户ID
     * @return http返回体，其中的data为所有用户信息的列表
     */
    BaseResponse getAllUserInfoExcludeSelf(int id);

    /**
     * 根据用户真实姓名删除该用户全部信息
     * @param username 用户真实姓名
     * @return http返回体
     */
    BaseResponse deleteUserByUserName(String username);

    /**
     * 根据用户真实姓名更新该用户信息
     * @param manageUserInfoResponseData 需更改的用户信息,其中name字段为用户真实姓名
     * @return http返回体
     */
    BaseResponse updateUserInfo(ManageUserInfoResponseData manageUserInfoResponseData);
}
