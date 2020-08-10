package com.laughing.laughingsso.config;


import com.laughing.laughingsso.service.MyPasswordEncoderService;
import com.laughing.laughingsso.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Fu zihao
 * @version 1.0
 * @Description:
 * @date 20202020/8/6 17:01
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyUserDetailService userDetailService;

    @Autowired
    private MyPasswordEncoderService myPasswordEncoder;

    @Autowired
    private VerifyCodeFilter verifyCodeFilter;


    @Override
    public void configure(WebSecurity web) throws Exception {
        //  web.ignoring 不进行拦截（一般对于静态文件）
        web.ignoring().antMatchers("/js/**", "/css/**", "/images/**", "/favicon.ico");
        // 验证码支持
        web.ignoring().antMatchers("/verifyCode", "/send");
        // 注册页面可访问
        web.ignoring().antMatchers("/register.html", "/register/user");
        // 登录失败页面
        web.ignoring().antMatchers("/error.html");
    }

    /**
     * 用户授权方法1
     * @return
     */
/*    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("laughing")
                .password(new BCryptPasswordEncoder().encode("123456"))
                .roles("admin");

    }*/

    /**
     * 用户授权方法2 InMemoryUserDetailsManager内存方式
     * @return
     */
/*    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        // 在内存中使用
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("laughing").password("123456").roles("admin").build());
        return manager;
    }*/

    /**
     * 用户授权方法3 JdbcUserDetailsManager方式
     *
     * @return
     */
/*    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager();
        // 会在数据库中新建用户
        if(!manager.userExists("laughing")){
            manager.createUser(User.withUsername("laughing").password("123456").roles("admin").build());
        }
        return manager;
    }*/
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService);
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        //对默认的UserDetailsService进行覆盖
        authenticationProvider.setUserDetailsService(userDetailService);
        authenticationProvider.setPasswordEncoder(myPasswordEncoder);
        return authenticationProvider;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //
        http.addFilterBefore(verifyCodeFilter, UsernamePasswordAuthenticationFilter.class);
        http.authorizeRequests()
                // 用户访问权限
                .antMatchers("/admin/**").hasRole("admin")
                .antMatchers("/user/**").hasRole("user")
                .anyRequest().authenticated()
                .and()

                .formLogin()
                // action也是login.html .loginProcessingUrl()也可以指定
                .loginPage("/login.html")
                .defaultSuccessUrl("/") // 重定向
                .failureUrl("/error.html")
                .permitAll()
                .and()
                .logout()
                // .logoutUrl() 默认为 /logout的get请求

                .and()
                .csrf().disable();
    }


}
