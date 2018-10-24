package com.wangcheng.zeus.core.config.authentication.constant;

/**
 * @author: Administrator
 * @date: 2018/10/11 21:09
 * @description:
 */
public interface ZeusSecurityConstants {
    /**未登陆跳转到的url*/
    String DEFAULT_UNAUTHENTICATION_URL = "/authentication/require";
    /**浏览器登陆的url*/
    String DEFAULT_LOGIN_PROCESSING_URL_FORM = "/authentication/login";
    /**手机登陆的url*/
    String DEFAULT_LOGIN_PROCESSING_URL_MOBILE = "/authentication/mobile";
    /**验证码前缀*/
    String VALIDATE_CODE_URL_PREFIX = "/code/";
}
