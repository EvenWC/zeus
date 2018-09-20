package com.wangcheng.zeus.common.callback;

import com.wangcheng.zeus.common.response.ResponseModel;

/**
 * @Auther: Administrator
 * @Date: 2018/8/28 21:18
 * @Description: 提供给control 进行回调执行方法
 */
@FunctionalInterface
public interface ResponseCallBack<T> {

    ResponseModel<T> success(ResponseModel<T> ResponseModel);

    default ResponseModel<T> fail(ResponseModel<T> ResponseModel){
        return ResponseModel;
    }
}
