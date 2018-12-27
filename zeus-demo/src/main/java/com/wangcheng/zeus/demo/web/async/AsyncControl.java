package com.wangcheng.zeus.demo.web.async;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.sun.org.apache.regexp.internal.RE;
import com.wangcheng.zeus.common.response.ResponseModel;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: Administrator
 * @Date: 2018/9/16 12:33
 * @Description: 异步处理的rest
 */
@RestController
@RequestMapping("/order")
public class AsyncControl {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DefferedResultHolder defferedResultHolder;

    @Autowired
    private MockQueue mockQueue;
    //使用Callable来处理信息
    @PostMapping
    public Callable<String> handleOrder(String id){

        logger.info("主线程开始工作");
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                logger.info("副线程开始工作");
                TimeUnit.SECONDS.sleep(1);
                logger.info("副线程结束");
                return "success";
            }
        };
        logger.info("主线程结束工作");
        return callable;
    }

    @PostMapping("/deferredResult" )
    public DeferredResult<ResponseModel<String>> handleOrder(){
        //随机生成订单号
        String orderNo = RandomStringUtils.random(8);
        mockQueue.setOrderInfo(orderNo);
        DeferredResult<ResponseModel<String>> res = new DeferredResult();
        defferedResultHolder.getMap().put(orderNo,res);
        return res;
    }

}
