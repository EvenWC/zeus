package com.wangcheng.zeus.core.config.validate.code.manager;

import org.springframework.stereotype.Component;

/**
 * @author : Administrator
 * @Date: 2018/9/27 21:59
 * @Description: 短信发送器
 */
@Component
public class SmsSender {

    public void sendSms(String validateCode){
        //todo：真实的环境下面需要短信运营商的支持
        System.out.println("发送验证码:" + validateCode);
    }

}
