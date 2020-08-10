package com.laughing.laughingsso.controller;

import com.laughing.laughingsso.dao.PhoneCode;
import com.laughing.laughingsso.service.PhoneCodeService;
import com.laughing.laughingsso.service.SendSmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author Fu zihao
 * @version 1.0
 * @Description:
 * @date 20202020/8/10 12:41
 */
@RestController
public class SendPhoneCodeController {

    @Autowired
    private SendSmsService sendSms;

    @Autowired
    private PhoneCodeService phoneCodeService;

    @Autowired
    private PhoneCode phoneCode;

    @PostMapping("/send")
    public void SendMsg(@RequestParam("phone") String phone) {
        // 四位数验证码
        String verificationCode = String.valueOf((int) ((Math.random() * 9 + 1) * 1000));
        String[] phoneNumbers = {"+86"+phone};
        String[] templateParams = {verificationCode};
        String templateId = "662439";
        sendSms.sendMsg(templateId, phoneNumbers, templateParams);
        phoneCode.setCode(verificationCode);
        phoneCode.setPhone(phone);
        phoneCode.setTime(new Date());
        phoneCodeService.savePhoneCode(phoneCode);
    }
}
