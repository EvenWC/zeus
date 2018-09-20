package com.wangcheng.zeus.demo.domain;


import com.fasterxml.jackson.annotation.JsonView;
import com.wangcheng.zeus.common.response.ResponseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Auther: Administrator
 * @Date: 2018/9/13 21:23
 * @Description:
 */
@Table(name = "t_user")
@Entity
@ApiModel("用户信息")
public class User extends BaseEntity{
    @ApiModelProperty(value = "姓名")
    private String name;
    @ApiModelProperty(value = "用户名")
    private String userName;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "性别")
    private String gender;
    @ApiModelProperty(value = "年龄")
    private String age;
    @ApiModelProperty(value = "邮箱")
    private String email;
    @ApiModelProperty(value = "电话号码")
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
