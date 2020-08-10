package com.laughing.laughingsso.controller;

import com.laughing.laughingsso.service.PhoneCodeService;
import com.laughing.laughingsso.service.UserService;
import com.laughing.laughingsso.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Fu zihao
 * @version 1.0
 * @Description:
 * @date 20202020/8/7 15:40
 */
@RestController
public class RegisterController {

    @Autowired
    private UserService userService;


    @Autowired
    private PhoneCodeService phoneCodeService;

    @PostMapping("/register/user")
    public String RegisterUser(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               @RequestParam("phone") String phone,
                               @RequestParam("code") String code) {
        if (username.equals("") && username == "") {
            return "用户名不能为空";
        }
        if (password.equals("") && password == "") {
            return "密码不能为空";
        }
        if (phone.equals("") && phone == "") {
            return "手机号不能为空";
        }
        if (code.equals("") && code == "") {
            return "验证码不能为空";
        }
        if (!PasswordUtil.isCorrectUserName(username)) {
            return "用户名最多25个字符，不能包含空格，单双引号，问号等特殊符号";
        }
        if (!PasswordUtil.isCorrectPassword(username)) {
            return "密码要6到18位，只能包含字母数字，特殊符号";
        }
        if (!phoneCodeService.checkPhoneCode(phone, code)) {
            return "手机验证码不正确！";
        }


        int checkUser = userService.checkRegisterUser(username);
        int checkPhone = userService.checkRegisterUser("+86" + phone);

        if (checkUser == 0 && checkPhone == 0) {
            userService.registerUser(username, password, "+86" + phone);
            userService.userRole(username);
            // 注册成功
            return "注册成功";
        } else {
            return "用户名或手机号已被注册";
        }


    }
}
