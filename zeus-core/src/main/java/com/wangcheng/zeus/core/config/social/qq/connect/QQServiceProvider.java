package com.wangcheng.zeus.core.config.social.qq.connect;

import com.wangcheng.zeus.core.config.social.qq.api.QQ;
import com.wangcheng.zeus.core.config.social.qq.api.QQImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Template;

/**
 * @author: Administrator
 * @date: 2018/10/24 21:50
 * @description:
 */
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {

    private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";

    private static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";


    private String appId;

    public QQServiceProvider(String appId ,String appSecret) {
        super(new OAuth2Template( appId,  appSecret,  URL_AUTHORIZE,  URL_ACCESS_TOKEN));
        this.appId = appId;
    }

    @Override
    public QQ getApi(String accessToken) {
        return new QQImpl(appId,accessToken);
    }
}
