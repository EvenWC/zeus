package com.wangcheng.zeus.core.config.social.qq;

import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * @author: evan
 * @date: 2018/11/16 20:16
 * @description:
 */
public class ZeusSpringSocialConfigurer extends SpringSocialConfigurer {

    private String filterProcessesUrl;

    public ZeusSpringSocialConfigurer(String filterProcessesUrl){
        this.filterProcessesUrl = filterProcessesUrl;
    }
    @Override
    protected <T> T postProcess(T object) {
        SocialAuthenticationFilter socialAuthenticationFilter = (SocialAuthenticationFilter)super.postProcess(object);
        socialAuthenticationFilter.setFilterProcessesUrl(filterProcessesUrl);
        return (T)socialAuthenticationFilter;
    }
}
