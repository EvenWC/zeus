package com.wangcheng.zeus.utils;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Administrator
 * @Date: 2018/9/17 23:12
 * @Description:
 */
public class ZeusAuthorityUtils {

    public static List<GrantedAuthority> createAuthorityList(String... roles) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(roles.length);

        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }

        return authorities;
    }

}
