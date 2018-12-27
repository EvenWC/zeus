package com.wangcheng.zeus.core.config.social.process.provider;

import com.google.common.collect.Maps;
import com.wangcheng.zeus.core.config.authentication.account.LoginInfo;
import com.wangcheng.zeus.core.config.properties.BrowserProperties;
import com.wangcheng.zeus.core.config.properties.ZeusProperties;
import com.wangcheng.zeus.core.config.utils.TokenUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.ServiceProvider;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.security.SocialAuthenticationToken;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author: evan
 * @date: 2018/11/19 21:45
 * @description:
 */
public class ZeusSocialAuthenticationProvider implements AuthenticationProvider {

    private UsersConnectionRepository usersConnectionRepository;

    private SocialUserDetailsService userDetailsService;

    private ZeusProperties zeusProperties;

    public ZeusSocialAuthenticationProvider(UsersConnectionRepository usersConnectionRepository, SocialUserDetailsService userDetailsService,ZeusProperties zeusProperties) {
        this.usersConnectionRepository = usersConnectionRepository;
        this.userDetailsService = userDetailsService;
        this.zeusProperties = zeusProperties;
    }
    @Override
    public boolean supports(Class<? extends Object> authentication) {
        return SocialAuthenticationToken.class.isAssignableFrom(authentication);
    }

    /**
     * Authenticate user based on {@link SocialAuthenticationToken}
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.isInstanceOf(SocialAuthenticationToken.class, authentication, "unsupported authentication type");
        Assert.isTrue(!authentication.isAuthenticated(), "already authenticated");
        SocialAuthenticationToken authToken = (SocialAuthenticationToken) authentication;
        String providerId = authToken.getProviderId();
        Connection<?> connection = authToken.getConnection();

        String userId = toUserId(connection);
        if (userId == null) {
            throw new BadCredentialsException("Unknown access token");
        }

        UserDetails userDetails = userDetailsService.loadUserByUserId(userId);
        if (userDetails == null) {
            throw new UsernameNotFoundException("Unknown connected account id");
        }
        //生成token，传给前端
        Map<String,Object> claims = Maps.newHashMapWithExpectedSize(3);
        claims.put("username",userDetails.getUsername());
        //暂时写死一个
        claims.put("sessionId","session");
        BrowserProperties browser = zeusProperties.getBrowser();
        String accessToken = TokenUtils.generateJwtToken(claims, browser.getSecret(), browser.getRememeberMeExpireIn());
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setAccessToken(accessToken);
        loginInfo.setUsername(userDetails.getUsername());
        SocialAuthenticationToken token = new SocialAuthenticationToken(connection, userDetails, authToken.getProviderAccountData(), getAuthorities(providerId, userDetails));
        token.setDetails(loginInfo);
        return token;
    }

    protected String toUserId(Connection<?> connection) {
        List<String> userIds = usersConnectionRepository.findUserIdsWithConnection(connection);
        // only if a single userId is connected to this providerUserId
        return (userIds.size() == 1) ? userIds.iterator().next() : null;
    }

    /**
     * Override to grant authorities based on {@link ServiceProvider} id and/or a user's account id
     * @param providerId {@link ServiceProvider} id
     * @param userDetails {@link UserDetails} as returned by {@link SocialUserDetailsService}
     * @return extra authorities of this user not already returned by {@link UserDetails#getAuthorities()}
     */
    protected Collection<? extends GrantedAuthority> getAuthorities(String providerId, UserDetails userDetails) {
        return userDetails.getAuthorities();
    }


}
