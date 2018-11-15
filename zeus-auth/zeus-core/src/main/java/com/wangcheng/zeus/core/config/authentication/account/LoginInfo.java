package com.wangcheng.zeus.core.config.authentication.account;

import java.time.LocalDateTime;

/**
 * @author: Administrator
 * @date: 2018/11/7 21:21
 * @description:
 */
public class LoginInfo {

    private String accessToken;

    private String username;

    private LocalDateTime localDateTime = LocalDateTime.now();

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}
