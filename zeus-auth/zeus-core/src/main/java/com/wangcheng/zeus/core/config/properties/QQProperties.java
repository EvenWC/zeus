package com.wangcheng.zeus.core.config.properties;

import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * @author: Administrator
 * @date: 2018/10/28 14:15
 * @description:
 */
public class QQProperties extends SocialProperties {

    private String providerId = "qq";

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}
