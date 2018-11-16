package com.wangcheng.zeus.core.config.social.qq.connect;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @author: Administrator
 * @date: 2018/11/16 23:37
 * @description:
 */
public class QQOAuth2Template extends OAuth2Template {

    public QQOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
        super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
        //设置该参数，因为clientId 和 client_secret 是必填项，而父类判断了这个值必须是true才添加这两个参数
        super.setUseParametersForClientAuthentication(true);
    }

    /**
     * qq 互联获取到的数据是： access_token=FE04************************CCE2&expires_in=7776000&refresh_token=88E4************************BE14 格式的字符串
     * 详情： http://wiki.connect.qq.com/%E4%BD%BF%E7%94%A8authorization_code%E8%8E%B7%E5%8F%96access_token
     */
    @Override
    protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
        String tokenResult = getRestTemplate().postForObject(accessTokenUrl, parameters, String.class);
        String[] split = StringUtils.splitByWholeSeparatorPreserveAllTokens(tokenResult,"&" );
        String accessToken = StringUtils.substringAfterLast(split[0],"=");
        Long expiresIn = Long.valueOf(StringUtils.substringAfterLast(split[1],"="));
        String refreshToken = StringUtils.substringAfterLast(split[2],"=");
        return new AccessGrant(accessToken, null, refreshToken, expiresIn);
    }

    /**
     * 处理下面这个问题，
     * Could not extract response: no suitable HttpMessageConverter found for response type [interface java.util.Map] and content type [text/html]
     * 因为qq返回的信息是contentType 类型是 text/html，而默认不支持这种类型，所以复写这个方法，把支持text/html 的StringHttpMessageConverter 添加进去
     * @return
     */
    @Override
    protected RestTemplate createRestTemplate(){
        RestTemplate restTemplate = super.createRestTemplate();
        //qq返回的content-type 是
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        return restTemplate;
    }
}
