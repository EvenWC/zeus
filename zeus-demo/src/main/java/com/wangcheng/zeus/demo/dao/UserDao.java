package com.wangcheng.zeus.demo.dao;

import com.wangcheng.zeus.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: Administrator
 * @Date: 2018/9/13 21:24
 * @Description:
 */
public interface UserDao extends JpaRepository< User,Long> {
}
