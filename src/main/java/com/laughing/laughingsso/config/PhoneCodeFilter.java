package com.laughing.laughingsso.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laughing.laughingsso.service.SendSmsService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Fu zihao
 * @version 1.0
 * @Description:
 * @date 20202020/8/10 12:30
 */
public class PhoneCodeFilter extends GenericFilter {

    @Autowired
    private SendSmsService sendSms;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        if ("POST".equals(request.getMethod()) && (("/login.html".equals(request.getServletPath())) || ("/register/user".equals(request.getServletPath())))) {

            // 四位数验证码
            String verificationCode = String.valueOf((int) ((Math.random() * 9 + 1) * 1000));

            String phone = request.getParameter("phone");
            String phoneCode = request.getParameter("phone_code");

            String[] phoneNumbers = {phone};
            String[] templateParams = {verificationCode};
            String templateId = "662439";
            sendSms.sendMsg(templateId, phoneNumbers, templateParams);




        } else {
            filterChain.doFilter(request, response);
        }


    }
}
