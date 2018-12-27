package com.wangcheng.zeus;

import com.wangcheng.zeus.dao.UserDetailDao;
import com.wangcheng.zeus.domain.UserDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;


/**
 * @Auther: Administrator
 * @Date: 2018/9/13 21:15
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@SpringBootConfiguration
public class UserControlTest  {
    @Autowired
    private UserDetailDao userDao;


    @Test
    public void queryUser(){
        List<UserDetail> all = userDao.findAll();
        Assert.assertTrue(all.size()!=0);
    }

}
