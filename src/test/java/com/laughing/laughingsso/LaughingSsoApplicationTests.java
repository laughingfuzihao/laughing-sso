package com.laughing.laughingsso;

import com.laughing.laughingsso.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

@SpringBootTest
class LaughingSsoApplicationTests {

    /**
     * 加密编码
     * e10adc3949ba59abbe56e057f20f883e
     */
    @Test
    void contextLoads() {
        System.out.println(DigestUtils.md5DigestAsHex("123456".toString().getBytes()));

    }



}
