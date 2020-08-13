package com.laughing.laughingsso.controller;

/**
 * @author Fu zihao
 * @version 1.0
 * @Description:
 * @date 20202020/8/6 16:44
 */

import com.laughing.laughingsso.dao.User;
import com.laughing.laughingsso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class SuccessController {

    @GetMapping("/")
    public String success() {
        return "success";
    }

    @GetMapping("/error")
    public String error() {
        return "redirect:/";
    }

    /**
     * 方法授权 匿名访问
     */
    @PreAuthorize("isAnonymous()")
    @GetMapping("/test")
    public void test(){

    }

    /**
     * 方法授权 admin
     */
    @PreAuthorize("hasRole(admin)")
    @GetMapping("/test2")
    public void tes2t(){

    }


}
