package com.laughing.laughingsso.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.laughing.laughingsso.dao.PhoneCode;
import com.laughing.laughingsso.mapper.PhoneCodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Fu zihao
 * @version 1.0
 * @Description:
 * @date 20202020/8/10 12:49
 */
@Service
public class PhoneCodeService {
    @Autowired
    private PhoneCodeMapper phoneCodeMapper;

    public int savePhoneCode(PhoneCode phoneCode) {
        return phoneCodeMapper.insert(phoneCode);
    }

    /**
     * 校验手机验证码是否正确
     *
     * @return
     */

    public boolean checkPhoneCode(String phone, String code) {
        QueryWrapper<PhoneCode> wrapper = new QueryWrapper<>();
        wrapper.eq("code", code);
        wrapper.eq("phone", phone);
        if (phoneCodeMapper.selectList(wrapper).size() > 0) {
            return true;
        } else {
            return false;
        }
    }
}
