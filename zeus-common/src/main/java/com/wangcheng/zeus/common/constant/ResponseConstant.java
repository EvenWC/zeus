package com.wangcheng.zeus.common.constant;

/**
 * @Auther: Administrator
 * @Date: 2018/8/28 20:51
 * @Description: Response的常量
 */
public class ResponseConstant {

    /**
     * 正常响应
     */
    public static final int SUCCESS_CODE = 200;
    /**
     * 成功消息
     */
    public static final String SUCCESS_MESSAGE = "success";
    /**
     * 参数错误
     */
    public static final int VALIDATE_ERROR_CODE = 300;
    /**
     * 参数验证错误信息
     */
    public static final String VALIDATE_ERROR_MESSAGE = "parameter validate error： {}";
    /**
     * 代码异常
     */
    public static final int FAIL_CODE = 500;
    /**
     * 错误信息
     */
    public static final String FAIL_MESSAGE = "error message： {}";
    /**
     * 业务断言
     */
    public static final int BUSINESS_CODE = 403;
    /**
     * 业务消息
     */
    public static final String BUSINESS_MESSAGE = "business.error：{}";

}
