package site.evan.zeus.facade.domain;

import java.time.LocalDateTime;

/**
 * @author : evan
 * @Date: 2018/9/25 20:21
 * @Description:
 */
public class Token {
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
