package com.wangcheng.zeus.common.callback;

import com.wangcheng.zeus.common.response.ResponseModel;

/**
 * @author : Administrator
 * @Date: 2018/8/28 21:18
 * @Description: 提供给control 进行回调执行方法
 */
@FunctionalInterface
public interface ResponseCallBack<T> {
    /**
     * 操作成功的回调
     * @param responseModel
     * @return
     */
    ResponseModel<T> success(ResponseModel<T> responseModel);

    /**
     * 操作失败的回调
     * @param responseModel
     * @return
     */
    default ResponseModel<T> fail(ResponseModel<T> responseModel){
        return responseModel;
    }
}
