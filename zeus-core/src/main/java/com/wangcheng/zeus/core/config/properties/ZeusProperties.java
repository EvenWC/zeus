package com.wangcheng.zeus.core.config.properties;

import com.google.common.collect.Lists;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @Auther: Administrator
 * @Date: 2018/9/18 22:53
 * @Description: zeus 配置
 */
@ConfigurationProperties(prefix = "zeus")
public class ZeusProperties {

    private List<String> excludeURIs = Lists.newArrayList("/swagger-ui.html","/swagger-resources/configuration/ui","/v2/*","/swagger-resources");

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

    public List<String> getExcludeURIs() {
        return excludeURIs;
    }

    public void setExcludeURIs(List<String> excludeURIs) {
        this.excludeURIs = excludeURIs;
    }
}
