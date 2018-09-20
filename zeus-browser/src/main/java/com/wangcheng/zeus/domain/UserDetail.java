package com.wangcheng.zeus.domain;

import javax.persistence.*;
import java.util.Set;

/**
 * @Auther: Administrator
 * @Date: 2018/9/17 20:57
 * @Description:
 */
@Entity
@Table(name = "t_user")
public class UserDetail {

    @Id
    @GeneratedValue
    private Long id;

    private String userName;

    private String password;

    @OrderBy("id")
    @OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinColumn(name="userId")
    private Set<Role>  roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void setRoles() {
    }
}
