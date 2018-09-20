package com.wangcheng.zeus.domain;

import javax.persistence.*;
import java.util.Set;

/**
 * @Auther: Administrator
 * @Date: 2018/9/17 21:02
 * @Description:
 */
@Table(name = "t_role")
@Entity
public class Role {
    @Id
    @GeneratedValue
    private Long id;

    private Long userId;

    private String roleName;

    @OrderBy("id")
    @OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinColumn(name="roleId")
    private Set<Resource> resources;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<Resource> getResources() {
        return resources;
    }

    public void setResources(Set<Resource> resources) {
        this.resources = resources;
    }
}
