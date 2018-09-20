package com.wangcheng.zeus.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Auther: Administrator
 * @Date: 2018/9/17 21:05
 * @Description:
 */
@Table(name = "t_resource")
@Entity
public class Resource {

    @Id
    @GeneratedValue
    private Long id;

    private Long roleId;

    private String resourceName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }
}
