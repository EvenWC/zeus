package com.wangcheng.zeus.core.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Auther: Administrator
 * @Date: 2018/9/18 22:53
 * @Description: zeus 配置
 */
@ConfigurationProperties(prefix = "zeus")
public class ZeusProperties {

    private String[] excludeURIs = new String[]{"/swagger-ui.html","/swagger-resources/configuration/ui","/v2/*","/swagger-resources","/code/*","/user/register","/user/checkAccount","/qq/callback"};

    private BrowserProperties browser = new BrowserProperties();

    private ValidateCodeProperties validateCode = new ValidateCodeProperties();

    private SocialProperties social = new SocialProperties();

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }

    public ValidateCodeProperties getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(ValidateCodeProperties validateCode) {
        this.validateCode = validateCode;
    }

    public SocialProperties getSocial() {
        return social;
    }

    public void setSocial(SocialProperties social) {
        this.social = social;
    }

    public String[] getExcludeURIs() {
        return excludeURIs;
    }

    public void setExcludeURIs(String[] excludeURIs) {
        this.excludeURIs = excludeURIs;
    }
}
