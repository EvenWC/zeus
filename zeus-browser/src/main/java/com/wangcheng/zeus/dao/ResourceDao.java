package com.wangcheng.zeus.dao;

import com.wangcheng.zeus.domain.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: Administrator
 * @Date: 2018/9/17 22:37
 * @Description:
 */
public interface ResourceDao extends JpaRepository<Resource,Long> {
}
