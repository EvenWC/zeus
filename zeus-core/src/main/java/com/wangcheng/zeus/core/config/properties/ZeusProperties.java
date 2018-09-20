package com.wangcheng.zeus.core.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Auther: Administrator
 * @Date: 2018/9/18 22:53
 * @Description: zeus 配置
 */
@ConfigurationProperties(prefix = "zeus")
public class ZeusProperties {

    private BrowserProperties browser;

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }
}
