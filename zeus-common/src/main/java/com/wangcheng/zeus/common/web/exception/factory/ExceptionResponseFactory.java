package com.wangcheng.zeus.common.web.exception.factory;

import com.wangcheng.zeus.common.web.exception.BusinessException;
import com.wangcheng.zeus.common.web.exception.strategy.ExceptionHandleStrategy;
import com.wangcheng.zeus.common.web.exception.strategy.annotation.ExceptionHandle;
import com.wangcheng.zeus.common.web.exception.strategy.impl.DefaultExceptionHandleStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Administrator
 * @Date: 2018/8/30 21:25
 * @Description: 负责生产处理异常的策略对象
 */
public enum ExceptionResponseFactory {
    INSTANCE;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private static final String STRATEGY_BASE_PACKAGE = "com.wangcheng.zeus.common.web.exception.strategy.impl";

    private ClassLoader classLoader = getClass().getClassLoader();

    private List<Class<? extends ExceptionHandleStrategy>> strategies ;

    ExceptionResponseFactory(){
        init();
    }

    //初始化
    private void init(){
        //加载策略接口
        Class<?> strategyClz = null;
        try {
            strategyClz = classLoader.loadClass(ExceptionHandleStrategy.class.getName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("策略接口未找到");
        }
        strategies = new ArrayList();
        File[] basePackageClz = getBasePackageClz();
        for (int i = 0; i < basePackageClz.length; i++) {
            File file = basePackageClz[i];
            try {
                String name = file.getName();
                String clzName = name.substring(0, name.length() - ".class".length());
                Class<?> clz = classLoader.loadClass(STRATEGY_BASE_PACKAGE + "." + clzName);
                if(strategyClz.isAssignableFrom(clz) && clz != strategyClz){
                    strategies.add((Class<? extends ExceptionHandleStrategy>)clz);
                }
            } catch (ClassNotFoundException e) {
                logger.warn("load class error,class name: {}",STRATEGY_BASE_PACKAGE + "." +file.getName());
            }
        }
    }

    /**
     * 获取策略包下的所有class文件
     * @return
     */
    private File[] getBasePackageClz(){
        try {
            File file = new File(classLoader.getResource(STRATEGY_BASE_PACKAGE.replace(".","/")).toURI());
            return file.listFiles(( dir,  name) -> name.endsWith(".class"));
        } catch (URISyntaxException e) {
            throw new RuntimeException("未找到资源");
        }
    }

    /**
     * 通过异常的类型来返回对应的ResponseModel
     * @param e
     * @return
     */
    public ExceptionHandleStrategy createExceptionHandler(Exception e){

        for (Class<? extends ExceptionHandleStrategy> strategy : strategies) {
            ExceptionHandle exceptionHandle = handleAnnotation(strategy);
            Class<? extends Exception>[] value = exceptionHandle.value();
            for (int i = 0; i < value.length; i++) {
                if(value[i].equals(e.getClass())){
                    try {
                        return strategy.newInstance();
                    } catch (Exception e1) {
                        throw new RuntimeException("实例化策略失败");
                    }
                }
            }
        }
        return new DefaultExceptionHandleStrategy();
    }

    private ExceptionHandle handleAnnotation(Class<? extends  ExceptionHandleStrategy> clz){
        ExceptionHandle annotation = clz.getDeclaredAnnotation(ExceptionHandle.class);
        return annotation;
    }

    public static void main(String[] args) {
        BusinessException businessException = new BusinessException("业务出现异常啦。。。");
        ExceptionHandleStrategy exceptionHandler = INSTANCE.createExceptionHandler(businessException);
        System.out.println(exceptionHandler.handle(businessException));
    }

}
