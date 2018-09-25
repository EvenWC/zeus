package com.wangcheng.zeus.dao;

import com.wangcheng.zeus.domain.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

/**
 * @author : Administrator
 * @Date: 2018/9/25 20:21
 * @Description:
 */
public interface TokenDao extends JpaRepository<Token,Long> {

    @Query("update Token set expireDateTime = ?2 where username = ?1")
    int updateExpireDateTimeByUserName(String username, LocalDateTime expireDateTime);


}
