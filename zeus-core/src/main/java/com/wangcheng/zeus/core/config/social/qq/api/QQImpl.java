package com.wangcheng.zeus.core.config.social.qq.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.io.IOException;

/**
 * @author: Administrator
 * @date: 2018/10/24 21:07
 * @description:
 */
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {
    /**
     * 获取用户基本信息
     * 文档地址： http://wiki.connect.qq.com/get_user_info
     */
    private static final String URL_GET_USER_INFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";

    private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";
    /**
     * appId
     */
    private String appId;

    private String openid;

    private ObjectMapper objectMapper = new ObjectMapper();

    public QQImpl(String appId,String accessToken){
        super(accessToken,TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appId = appId;
        //获取openid
        String url = String.format(URL_GET_OPENID,accessToken);
        String result = getRestTemplate().getForObject(url, String.class);
        System.out.println(result);

        String openid = StringUtils.substringBetween("\"openid\":\"", "}");
        this.openid = openid;
    }

    @Override
    public QQUserInfo getQQUserInfo(){
        String url = String.format(URL_GET_USER_INFO,appId,openid);
        String result = getRestTemplate().getForObject(url, String.class);
        try {
            QQUserInfo userInfo = objectMapper.readValue(result, QQUserInfo.class);
            userInfo.setOpenId(openid);
            return userInfo;
        } catch (IOException e) {
            throw new RuntimeException("获取用户信息失败");
        }
    }
}
