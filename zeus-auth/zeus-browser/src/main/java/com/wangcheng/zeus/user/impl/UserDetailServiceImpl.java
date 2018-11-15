package com.wangcheng.zeus.user.impl;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.wangcheng.zeus.dao.UserDetailDao;
import com.wangcheng.zeus.domain.Resource;
import com.wangcheng.zeus.domain.Role;
import com.wangcheng.zeus.domain.UserDetail;
import com.wangcheng.zeus.user.ZeusUserDetailService;
import com.wangcheng.zeus.user.ZeusUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author : evan
 * @Date: 2018/9/17 20:47
 * @Description: 基于关系型数据库的UserDetailService
 */
@Service
public class UserDetailServiceImpl implements ZeusUserDetailService ,SocialUserDetailsService {

    @Autowired
    private UserDetailDao userDao;

   // @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ZeusUserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserDetail user = userDao.findByUsernameOrTelNo(userName,userName);
        if(Objects.isNull(user)){
            throw new UsernameNotFoundException(userName + "没有找到");
        }
        return buildZeusUserDetails( user);
    }



    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        UserDetail user = userDao.findOne(Long.valueOf(userId));
        if(Objects.isNull(user)){
            throw new UsernameNotFoundException(userId + "没有找到");
        }
        return buildZeusUserDetails( user);
    }

    private ZeusUserDetails buildZeusUserDetails( UserDetail userDetail) {
        Set<Role> roles = userDetail.getRoles();
        List<GrantedAuthority> authorities = Lists.newLinkedList();
        roles.forEach(role -> {
            Set<Resource> resources = role.getResources();
            List<String> resourceList = Lists.newLinkedList();
            resources.forEach(resource -> resourceList.add(resource.getResourceName()));
            String resourceString = Joiner.on(",").join(resourceList);
            authorities.add(new SimpleGrantedAuthority(resourceString));
        });
        return  new ZeusUserDetails(userDetail.getId() + "",userDetail.getUsername(),userDetail.getPassword(),authorities);
    }
}
