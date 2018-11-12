package com.wangcheng.zeus.core.config.properties;

/**
 * @author : evan
 * @Date: 2018/9/18 22:55
 * @Description:
 */
public class BrowserProperties {

    private long rememeberMeExpireIn = 60 * 60 * 24 * 5;

    private boolean serverSaveToken = true;

    private String secret = "525qz";

    public long getRememeberMeExpireIn() {
        return rememeberMeExpireIn;
    }

    public void setRememeberMeExpireIn(long rememeberMeExpireIn) {
        this.rememeberMeExpireIn = rememeberMeExpireIn;
    }

    public boolean isServerSaveToken() {
        return serverSaveToken;
    }

    public void setServerSaveToken(boolean serverSaveToken) {
        this.serverSaveToken = serverSaveToken;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
