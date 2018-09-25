package com.wangcheng.zeus.remeberme;

import com.wangcheng.zeus.dao.TokenDao;
import com.wangcheng.zeus.domain.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author : Administrator
 * @Date: 2018/9/25 20:32
 * @Description: token管理器
 */
@Component
public class TokenManager {

    @Autowired
    private TokenDao tokenDao;

    public void saveToken(Token token){
        tokenDao.saveAndFlush(token);
    }

    public void updateExpireDateTime(String username,Long expireIn){

    }

}
