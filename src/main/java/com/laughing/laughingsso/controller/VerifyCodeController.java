package com.laughing.laughingsso.controller;

import com.laughing.laughingsso.util.VerifyCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author Fu zihao
 * @version 1.0
 * @Description:
 * @date 20202020/8/10 9:49
 */
@RestController
public class VerifyCodeController {

    private final VerifyCode verifyCode;

    public VerifyCodeController(VerifyCode verifyCode) {
        this.verifyCode = verifyCode;
    }

    /**
     * 生成验证码
     * @param session
     * @param response
     * @throws IOException
     */
    @GetMapping("/verifyCode")
    public void getVerifyCode(HttpSession session, HttpServletResponse response) throws IOException {

        BufferedImage image = verifyCode.getImage();
        String code = verifyCode.getText();
        // 放入session中
        session.setAttribute("verify_code", code);
        VerifyCode.output(image, response.getOutputStream());

    }


}
