package com.euler.service;

import com.euler.domain.BaseResponse;
import com.euler.domain.ManageUserInfoResponseData;
import com.euler.domain.User;
import com.euler.domain.UserInfo;
import com.euler.repository.UserInfoRepository;
import com.euler.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户服务实现类
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/3/25
 */
@Service
public class UserServiceImpl implements UserService {
    final UserRepository userRepository;
    final UserInfoRepository userInfoRepository;

    public UserServiceImpl(UserRepository userRepository, UserInfoRepository userInfoRepository) {
        this.userRepository = userRepository;
        this.userInfoRepository = userInfoRepository;
    }

    @Override
    public BaseResponse getAllUserInfo() {
        List<ManageUserInfoResponseData> ls = userRepository.findAll().stream().map(user -> {
            UserInfo userInfo = userInfoRepository.findById(user.getId()).get();
            ManageUserInfoResponseData responseData = new ManageUserInfoResponseData();
            responseData.setName(userInfo.getUserName());
            responseData.setPassword(user.getPassword());
            responseData.setMail(userInfo.getMail());
            responseData.setBirthday(userInfo.getBirthday());
            responseData.setBalance(userInfo.getBalance());
            return responseData;
        }).collect(Collectors.toList());
        return new BaseResponse(HttpServletResponse.SC_OK, "数据获取成功", ls);
    }

    @Override
    public BaseResponse getAllUserInfoExcludeSelf(int id) {
        List<ManageUserInfoResponseData> ls = userRepository.findAll().stream()
                .filter(user -> user.getId() != id)
                .map(user -> {
                    UserInfo userInfo = userInfoRepository.findById(user.getId()).get();
                    ManageUserInfoResponseData responseData = new ManageUserInfoResponseData();
                    responseData.setName(userInfo.getUserName());
                    responseData.setPassword(user.getPassword());
                    responseData.setMail(userInfo.getMail());
                    responseData.setBirthday(userInfo.getBirthday());
                    responseData.setBalance(userInfo.getBalance());
                    return responseData;
                }).collect(Collectors.toList());
        return new BaseResponse(HttpServletResponse.SC_OK, "数据获取成功", ls);
    }

    /**
     * 根据用户信息表中的user_name字段删除两个表中该用户的全部信息
     *
     * @param username 用户真实姓名
     * @return
     */
    @Override
    public BaseResponse deleteUserByUserName(String username) {
        List<UserInfo> userInfoList = userInfoRepository.findUserByUserName(username);
        if (userInfoList.isEmpty()) {
            return new BaseResponse(HttpServletResponse.SC_BAD_REQUEST, "该用户不存在,删除失败");
        }
        //用户ID
        Integer userId = userInfoList.get(0).getUserId();
        //两个表都删除该用户(数据库触发器没成功)
        userRepository.deleteById(userId);
        userInfoRepository.deleteById(userId);
        return new BaseResponse(HttpServletResponse.SC_OK, "删除成功");
    }

    @Override
    public BaseResponse updateUserInfo(ManageUserInfoResponseData manageUserInfoResponseData) {
        // 用户真实姓名
        String user_name = manageUserInfoResponseData.getName();
        // 根据用户真实姓名获取该数据库中该用户信息实体列表
        List<UserInfo> userInfoList = userInfoRepository.findUserByUserName(user_name);
        if (userInfoList.isEmpty()) {
            return new BaseResponse(HttpServletResponse.SC_BAD_REQUEST, "该用户不存在,修改失败");
        }

        UserInfo userInfo = userInfoList.get(0);
        //修改该用户信息
        userInfo.setMail(manageUserInfoResponseData.getMail());
        userInfo.setBirthday(manageUserInfoResponseData.getBirthday());
        userInfo.setBalance(manageUserInfoResponseData.getBalance());

        userInfoRepository.saveAndFlush(userInfo);
        return new BaseResponse(HttpServletResponse.SC_OK, "修改成功");
    }

    @Override
    public BaseResponse updateBaseInfo(Integer id, String nickname, String mail, String birthday) {
        UserInfo userInfo= userInfoRepository.findById(id).orElse(null);
       if(null==userInfo){
           return new BaseResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "数据库异常,查找不到当前登录用户id");
       }
        userInfo.setNickname(nickname);
        userInfo.setMail(mail);
        userInfo.setBirthday(birthday);
        userInfoRepository.saveAndFlush(userInfo);
        return new BaseResponse(HttpServletResponse.SC_OK, "信息修改成功");

    }

    @Override
    public BaseResponse updatePassword(Integer id, String oldPassword, String newPassword) {
        User user=userRepository.findById(id).orElse(null);
        if(null==user){
            return new BaseResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "数据库异常,查找不到当前登录用户id");
        }
        if(!user.getPassword().equals(oldPassword)){
            return new BaseResponse(HttpServletResponse.SC_FORBIDDEN, "密码错误");
        }
        user.setPassword(newPassword);
        userRepository.saveAndFlush(user);
        return new BaseResponse(HttpServletResponse.SC_OK, "密码修改成功");
    }
}
