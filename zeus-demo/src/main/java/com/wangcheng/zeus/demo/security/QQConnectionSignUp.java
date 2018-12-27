package com.wangcheng.zeus.demo.security;

import com.wangcheng.zeus.demo.domain.User;
import com.wangcheng.zeus.demo.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;
import java.util.UUID;

/**
 * @author: evan
 * @date: 2018/11/17 23:00
 * @description:
 */
@Component
public class QQConnectionSignUp implements ConnectionSignUp {

    @Autowired
    private UserManager userManager;

    @Override
    public String execute(Connection<?> connection) {
        User user =  new User();
        user.setName(connection.getDisplayName());
        user.setUsername(connection.getDisplayName());
        user.setPassword(UUID.randomUUID().toString());
        user.setGender("未知的性别");
        userManager.add(user);
        return String.valueOf(user.getId());
    }
}
