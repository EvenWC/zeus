package com.wangcheng.zeus.core.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Auther: Administrator
 * @Date: 2018/9/18 22:53
 * @Description: zeus 配置
 */
@ConfigurationProperties(prefix = "zeus")
public class ZeusProperties {

    private BrowserProperties browser = new BrowserProperties();

    private ValidateCodeProperties validateCode = new ValidateCodeProperties();

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
}
