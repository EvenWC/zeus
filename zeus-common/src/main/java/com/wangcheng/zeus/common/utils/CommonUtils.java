package com.wangcheng.zeus.common.utils;

import org.slf4j.helpers.MessageFormatter;

/**
 * @author : Administrator
 * @Date: 2018/8/29 21:00
 * @Description：工具类
 */
public class CommonUtils {


    private CommonUtils(){
        throw new AssertionError();
    }

    /**
     * 格式化一个带有占位符的字符串
     * @param message 原始字符串
     * @param args 占位符替换值
     * @return 格式化之后的结果
     */
    public static String format(String message,Object... args){
        return MessageFormatter.arrayFormat(message,args).getMessage();
    }


}
