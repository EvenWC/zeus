package com.wangcheng.zeus.demo.domain;


import com.fasterxml.jackson.annotation.JsonView;
import com.wangcheng.zeus.common.response.ResponseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author : evan
 * @Date: 2018/9/13 21:23
 * @Description:
 */
@Table(name = "t_user")
@Entity
@ApiModel("用户信息")
public class User extends BaseEntity{
    @ApiModelProperty(name = "姓名")
    private String name;
    @ApiModelProperty(name = "用户名")
    private String username;
    @ApiModelProperty(name = "密码")
    private String password;
    @ApiModelProperty(name = "性别")
    private String gender;
    @ApiModelProperty(name = "年龄")
    private String age;
    @ApiModelProperty(name = "邮箱")
    private String email;
    @ApiModelProperty(name = "电话号码")
    private String telNo;
    @JsonView(value =  ResponseModel.SimpleInfo.class)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonView(value = ResponseModel.SimpleDetailInfo.class)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonView(value = ResponseModel.SimpleInfo.class)
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    @JsonView(value =ResponseModel. SimpleInfo.class)
    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @JsonView(value = ResponseModel.SimpleInfo.class)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonView(value = ResponseModel.SimpleInfo.class)
    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
