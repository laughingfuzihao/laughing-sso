package com.laughing.laughingsso.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Fu zihao
 * @version 1.0
 * @Description: 验证码过滤器
 * @date 2020/8/10 10:06
 */
@Component
public class VerifyCodeFilter extends GenericFilter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        // 如果是登录请求才走filterChain
        if ("POST".equals(request.getMethod()) && ("/login.html".equals(request.getServletPath()))) {
            // 用户输入的验证码  toLowerCase统一转换为小写比较
            String code = request.getParameter("code").toLowerCase();
            // 生成的图片验证码
            String verifyCode = ((String) request.getSession().getAttribute("verify_code")).toLowerCase();
            if (code == null || code == "" || !verifyCode.equals(code)) {
                // 验证码不正确
                response.setContentType("application/json;charset=utf-8");
                PrintWriter printWriter = response.getWriter();
                printWriter.write(new ObjectMapper().writeValueAsString("验证码错误！"));
                printWriter.flush();
                printWriter.close();
                return;
            } else {
                filterChain.doFilter(request, response);
            }
        } else {
            filterChain.doFilter(request, response);
        }


    }
}
