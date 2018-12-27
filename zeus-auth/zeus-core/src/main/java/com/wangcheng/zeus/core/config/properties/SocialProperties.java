package com.wangcheng.zeus.core.config.properties;

/**
 * @author: Administrator
 * @date: 2018/10/28 14:17
 * @description:
 */
public class SocialProperties {

    private String filterProcessesUrl = "/auth";

    private QQProperties qq = new QQProperties();

    public QQProperties getQq() {
        return qq;
    }

    public void setQq(QQProperties qq) {
        this.qq = qq;
    }
    public String getFilterProcessesUrl() {
        return filterProcessesUrl;
    }
    public void setFilterProcessesUrl(String filterProcessesUrl) {
        this.filterProcessesUrl = filterProcessesUrl;
    }
}
