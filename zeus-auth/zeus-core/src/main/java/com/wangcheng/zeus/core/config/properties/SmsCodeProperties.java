package com.wangcheng.zeus.core.config.properties;

/**
 * @author : Administrator
 * @Date: 2018/9/27 20:53
 * @Description:
 */
public class SmsCodeProperties  {
    /**需要验证码的url*/
    private String urls  ;
    /**验证码的长度*/
    private Integer length = 6;
    /**过期时间*/
    private Long expireIn = 30L;

    public String getUrls() {
        return urls;
    }

    public void setUrls(String urls) {
        this.urls = urls;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Long getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(Long expireIn) {
        this.expireIn = expireIn;
    }
}
