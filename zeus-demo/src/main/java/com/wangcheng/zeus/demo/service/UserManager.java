package com.wangcheng.zeus.demo.service;

import com.wangcheng.zeus.demo.dao.UserDao;
import com.wangcheng.zeus.demo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author : Administrator
 * @Date: 2018/9/13 21:28
 * @Description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserManager {

    @Autowired
    private UserDao userDao;

    public List<User> findAll(){
        List<User> all = userDao.findAll();
        return all;
    }

    public Boolean add(User user) {
        return userDao.save(user) != null;
    }

    public Boolean update(User user){
        return userDao.saveAndFlush(user) != null;
    }

    public Boolean delete(Long id){
         userDao.delete(id);
         return true;
    }

    public User find(Long id) {
        return userDao.findOne(id);
    }

}
