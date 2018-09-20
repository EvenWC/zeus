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
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import java.util.List;
import java.util.Set;

/**
 * @Auther: Administrator
 * @Date: 2018/9/17 20:47
 * @Description: 基于关系型数据库的UserDetailService
 */
@Component
public class DBUserDetailService implements ZeusUserDetailService {

    @Autowired
    private UserDetailDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ZeusUserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        List<UserDetail> userDetails = userDao.findByUserName(userName);
        if(CollectionUtils.isEmpty(userDetails)){
            throw new UsernameNotFoundException(userName + "没有找到");
        }
        UserDetail userDetail = userDetails.get(0);
        Set<Role> roles = userDetail.getRoles();
        List<GrantedAuthority> authorities = Lists.newLinkedList();
        roles.forEach(role -> {
            Set<Resource> resources = role.getResources();
            List<String> resourceList = Lists.newLinkedList();
            resources.forEach(resource -> resourceList.add(resource.getResourceName()));
            String resourceString = Joiner.on(",").join(resourceList);
            authorities.add(new SimpleGrantedAuthority(resourceString));
        });

        ZeusUserDetails zeusUserDetails = new ZeusUserDetails(userDetail.getUserName(),passwordEncoder.encode(userDetail.getPassword()),authorities);
        return zeusUserDetails;
    }
}
