package com.wangcheng.zeus.demo.domain;
import com.fasterxml.jackson.annotation.JsonView;
import com.wangcheng.zeus.common.response.ResponseModel;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: Administrator
 * @Date: 2018/9/13 21:25
 * @Description:
 */
@MappedSuperclass
public class BaseEntity implements Serializable {
    private Long id;

    private Date gmtCreate;

    private Date gmtModify;

    @Id
    @GeneratedValue
    @JsonView(value = ResponseModel.SimpleInfo.class)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonView(value =  ResponseModel.SimpleInfo.class)
    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    @JsonView(value =  ResponseModel.SimpleInfo.class)
    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }
}
