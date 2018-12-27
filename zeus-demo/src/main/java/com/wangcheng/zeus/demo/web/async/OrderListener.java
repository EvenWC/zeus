package com.wangcheng.zeus.demo.web.async;

import com.wangcheng.zeus.common.response.ResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: Administrator
 * @Date: 2018/9/16 13:11
 * @Description:
 */
@Component
public class OrderListener implements ApplicationListener<ApplicationContextEvent> {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DefferedResultHolder defferedResultHolder;

    @Override
    public void onApplicationEvent(ApplicationContextEvent applicationContextEvent) {

        new Thread(()->{

            while (true){
                if(mockQueue.getCompletOrderInfo() != null){
                    logger.info("监听到订单已经被处理，返回处理结果");
                    defferedResultHolder.getMap().get(mockQueue.getCompletOrderInfo()).setResult(ResponseModel.SUCCESS("监听到订单完成，订单号"+mockQueue.getCompletOrderInfo()));
                    mockQueue.setCompletOrderInfo(null);
                }else {
                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

}
