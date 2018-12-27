package com.wangcheng.zeus.demo.web.async;

import com.google.common.collect.Maps;
import com.wangcheng.zeus.common.response.ResponseModel;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Map;

/**
 * @Auther: Administrator
 * @Date: 2018/9/16 13:04
 * @Description:
 */
@Component
public class DefferedResultHolder {

    private Map<String,DeferredResult<ResponseModel<String>>> map = Maps.newHashMap();

    public Map<String, DeferredResult<ResponseModel<String>>> getMap() {
        return map;
    }
}
