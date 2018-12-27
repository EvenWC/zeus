package com.wangcheng.zeus.core.config.authentication.account;

/**
 * @author: Administrator
 * @date: 2018/11/5 21:53
 * @description:
 */
public class AccountAuthentication {
    /**
     * 账号：可能是 用户名、 手机号、邮箱
     */
    private String account;
    /**
     * 密码
     */
    private String password;
    /**
     * 记住我
     */
    private Boolean rememberme;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getRememberme() {
        return rememberme;
    }

    public void setRememberme(Boolean rememberme) {
        this.rememberme = rememberme;
    }

}
