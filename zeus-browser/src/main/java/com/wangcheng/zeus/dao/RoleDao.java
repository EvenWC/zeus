package com.wangcheng.zeus.dao;

import com.wangcheng.zeus.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: Administrator
 * @Date: 2018/9/17 22:36
 * @Description:
 */
public interface RoleDao extends JpaRepository<Role,Long> {
}
