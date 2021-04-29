package com.euler.service;

import com.euler.domain.BaseResponse;
import com.euler.domain.ManageUserInfoResponseData;

import javax.servlet.http.HttpSession;

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
     *
     * @param id 用户ID
     * @return http返回体，其中的data为所有用户信息的列表
     */
    BaseResponse getAllUserInfoExcludeSelf(int id);

    /**
     * 根据用户真实姓名删除该用户全部信息
     *
     * @param username 用户真实姓名
     * @return http返回体
     */
    BaseResponse deleteUserByUserName(String username);

    /**
     * 根据用户真实姓名更新该用户信息
     *
     * @param manageUserInfoResponseData 需更改的用户信息,其中name字段为用户真实姓名
     * @return http返回体
     */
    BaseResponse updateUserInfo(ManageUserInfoResponseData manageUserInfoResponseData);

    /**
     * 修改用户基本信息
     *
     * @param id       用户id
     * @param nickname 用户昵称
     * @param mail     邮箱
     * @param birthday 生日
     * @return http返回体
     */
    BaseResponse updateBaseInfo(Integer id, String nickname, String mail, String birthday);

    /**
     * 修改用户密码
     *
     * @param id 用户id
     * @param oldPassword 原密码
     * @param newPassword 新密码
     * @return http返回体
     */
    BaseResponse updatePassword(Integer id, String oldPassword, String newPassword);
}
