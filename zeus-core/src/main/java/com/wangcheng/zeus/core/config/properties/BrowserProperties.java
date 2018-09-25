package com.wangcheng.zeus.core.config.properties;

import com.wangcheng.zeus.core.config.properties.enums.LoginType;

/**
 * @Auther: Administrator
 * @Date: 2018/9/18 22:55
 * @Description:
 */
public class BrowserProperties {

    private String loginPage = "/default-login.html";

    private LoginType loginType = LoginType.JSON;

    private int rememeberMeExpireIn = 60 * 60 * 24 *5;

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }

    public int getRememeberMeExpireIn() {
        return rememeberMeExpireIn;
    }

    public void setRememeberMeExpireIn(int rememeberMeExpireIn) {
        this.rememeberMeExpireIn = rememeberMeExpireIn;
    }
}
