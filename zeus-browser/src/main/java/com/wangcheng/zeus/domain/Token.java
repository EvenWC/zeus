package com.wangcheng.zeus.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * @Auther: Administrator
 * @Date: 2018/9/25 20:21
 * @Description:
 */
@Entity
@Table(name = "t_token")
public class Token {
    @Id
    @GeneratedValue
    private Long id;
    /**用户名*/
    private String username;
    /**token*/
    private String token;
    /**过期时间*/
    private LocalDateTime expireDateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getExpireDateTime() {
        return expireDateTime;
    }

    public void setExpireDateTime(LocalDateTime expireDateTime) {
        this.expireDateTime = expireDateTime;
    }
}
