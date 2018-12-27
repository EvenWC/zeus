package com.wangcheng.zeus.common.web.exception;

/**
 * @author: evan
 * @Date: 2018/9/4 21:54
 * @Description: 业务断言时使用
 */
public class ZeusAsserts {

    private ZeusAsserts() throws IllegalAccessException {
        throw new IllegalAccessException("不能实例化");
    }

    public static void isTrue(Boolean condition,String message){
        if(!condition){
            throw new BusinessException(message);
        }
    }
    public static void isFalse(Boolean condition,String message){
        if(condition){
            throw new BusinessException(message);
        }
    }
}
