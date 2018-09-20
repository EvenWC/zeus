package com.wangcheng.zeus.demo.web.async;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: Administrator
 * @Date: 2018/9/16 12:41
 * @Description: 模拟消息队列
 */
@Component
public class MockQueue {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private String orderInfo;

    private String completOrderInfo;

    public String getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(String orderInfo) {
        logger.info("模拟消息队列获取订单信息");
        new Thread(()->{
           logger.info("模拟订单处理服务，开始处理订单");
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info("模拟订单处理服务，处理订单完成");
            this.completOrderInfo = orderInfo;
        }).start();
    }

    public String getCompletOrderInfo() {
        return completOrderInfo;
    }

    public void setCompletOrderInfo(String completOrderInfo) {
        this.completOrderInfo = completOrderInfo;
    }
}
