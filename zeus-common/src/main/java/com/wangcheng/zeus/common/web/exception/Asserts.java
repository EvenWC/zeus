package com.wangcheng.zeus.common.web.exception;

/**
 * @Auther: Administrator
 * @Date: 2018/9/4 21:54
 * @Description: 业务断言时使用
 */
public class Asserts {

    private Asserts(){
        throw new AssertionError("不能实例化业务断言类");
    }

    public static void assertTrue(Boolean condition,String message){
        if(!condition){
            throw new BusinessException(message);
        }
    }
    public static void assertFalse(Boolean condition,String message){
        if(condition){
            throw new BusinessException(message);
        }
    }
}
