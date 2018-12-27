package com.wangcheng.zeus.core.config.validate.code;

/**
 * @author : Administrator
 * @Date: 2018/9/27 20:48
 * @Description:验证码
 */
public class ValidateCode {
    /**
     * 和code一一对应，和验证码一起发给前端
     */
    private String token;
    /**验证码*/
    private String code;
    /**过期时间 单位：秒*/
    private Long expireIn;

    public ValidateCode(String token,String code, Long expireIn) {
        this.token = token;
        this.code = code;
        this.expireIn = expireIn;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(Long expireIn) {
        this.expireIn = expireIn;
    }
}
