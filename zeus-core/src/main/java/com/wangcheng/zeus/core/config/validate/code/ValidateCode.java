package com.wangcheng.zeus.core.config.validate.code;

import java.time.LocalDateTime;

/**
 * @author : Administrator
 * @Date: 2018/9/27 20:48
 * @Description:验证码
 */
public class ValidateCode {

    /**验证码*/
    private String code;
    /**过期时间*/
    private LocalDateTime expireTime;

    public ValidateCode(String code, Long expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

    public boolean isExpire(){
        return this.expireTime.isBefore(LocalDateTime.now());
    }
}
