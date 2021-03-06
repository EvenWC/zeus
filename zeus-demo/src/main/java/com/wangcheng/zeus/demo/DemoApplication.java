package com.wangcheng.zeus.demo;

import com.wangcheng.zeus.common.web.exception.register.EnableExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.session.data.gemfire.config.annotation.web.http.EnableGemFireHttpSession;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author : Administrator
 * @Date: 2018/9/12 21:49
 * @Description:
 */
@SpringBootApplication
@EntityScan({"com.wangcheng.zeus"})
@EnableJpaRepositories("com.wangcheng.zeus")
@EnableExceptionHandler
@ServletComponentScan(basePackages = "com.wangcheng.zeus")
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class,args);
    }
}
