package com.wangcheng.zeus.user;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @Auther: Administrator
 * @Date: 2018/9/17 20:43
 * @Description:
 */
public interface ZeusUserDetailService extends UserDetailsService {

    @Override
    ZeusUserDetails loadUserByUsername(String userName) throws UsernameNotFoundException;

}
