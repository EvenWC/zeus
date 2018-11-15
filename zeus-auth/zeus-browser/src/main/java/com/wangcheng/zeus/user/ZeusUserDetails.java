package com.wangcheng.zeus.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.security.SocialUserDetails;

import java.util.Collection;

/**
 * @Auther: Administrator
 * @Date: 2018/9/17 20:40
 * @Description:
 */
public class ZeusUserDetails implements UserDetails ,SocialUserDetails {

    /**用户id*/
    private String userId;
    /**用户名*/
    private String username;
    /**密码*/
    private String password;
    /**权限*/
    private Collection<? extends GrantedAuthority> authorities;
    /**账户没有过期*/
    private Boolean accountNonExpired;
    /**账户锁定*/
    private Boolean accountNonLocked;
    /**登录没有超时*/
    private Boolean credentialsNonExpired;
    /**账号启用*/
    private Boolean enabled;

    public ZeusUserDetails() {
    }

    public ZeusUserDetails(String userId,String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String getUserId() {
        return userId;
    }
}
