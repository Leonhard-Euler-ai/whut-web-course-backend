package com.euler.controller;

import com.euler.domain.BaseResponse;
import com.euler.domain.ManageUserInfoResponseData;
import com.euler.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import static com.euler.constant.Constant.SESSION_KEY_USER;

/**
 * 类描述
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/3/25
 */
@Controller
public class UserController {
    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/allUsers")
    @ResponseBody
    public BaseResponse getAllUserInfoExcludeSelf(HttpSession httpSession) {
        return userService.getAllUserInfoExcludeSelf(
                Integer.parseInt(httpSession.getAttribute(SESSION_KEY_USER).toString()));
    }

    @DeleteMapping("/api/deleteUser")
    @ResponseBody
    public BaseResponse deleteUser(@RequestParam String username){
        return userService.deleteUserByUserName(username);
    }

    @PostMapping("/api/updateUserInfo")
    @ResponseBody
    public BaseResponse updateUserInfo(@RequestBody ManageUserInfoResponseData manageUserInfoResponseData){
        return userService.updateUserInfo(manageUserInfoResponseData);
    }
}
