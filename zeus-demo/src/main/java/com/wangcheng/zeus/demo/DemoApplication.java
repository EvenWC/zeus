package com.wangcheng.zeus.demo;

import com.wangcheng.zeus.common.web.exception.strategy.annotation.EnableExceptionHandle;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author : Administrator
 * @Date: 2018/9/12 21:49
 * @Description:
 */
@SpringBootApplication(scanBasePackages = "com.wangcheng.zeus")
@EntityScan({"com.wangcheng.zeus"})
@EnableJpaRepositories("com.wangcheng.zeus")
@EnableExceptionHandle
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class,args);
    }

}
