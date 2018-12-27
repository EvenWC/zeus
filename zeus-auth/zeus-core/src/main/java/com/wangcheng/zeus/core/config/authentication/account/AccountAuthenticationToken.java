package com.wangcheng.zeus.core.config.authentication.account;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

import java.util.Collection;

/**
 * @author: evan
 * @date: 2018/11/5 21:02
 * @description:
 */
public class AccountAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    /**
     * 记住我
     */
    private Boolean rememberme;

    public AccountAuthenticationToken(Object account, Object password,Boolean rememberme) {
        super(account, password);
        this.rememberme = rememberme;
        super.setAuthenticated(false);
    }

    /**
     * 传入一个authentication
     * @param authentication
     */
    public AccountAuthenticationToken(AccountAuthentication authentication){
        this(authentication.getAccount(),authentication.getPassword(),authentication.getRememberme());
    }

    /**
     * 验证之后调用
     * @param account
     * @param password
     * @param authorities
     */
    public AccountAuthenticationToken(Object account, Object password, Collection<? extends GrantedAuthority> authorities) {
        super(account, password, authorities);
    }
}
