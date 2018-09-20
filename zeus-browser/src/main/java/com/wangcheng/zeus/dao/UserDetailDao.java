package com.wangcheng.zeus.dao;

import com.wangcheng.zeus.domain.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Auther: Administrator
 * @Date: 2018/9/17 20:55
 * @Description:
 */
public interface UserDetailDao extends JpaRepository<UserDetail,Long> {

    List<UserDetail> findByUserName(String userName);

}
