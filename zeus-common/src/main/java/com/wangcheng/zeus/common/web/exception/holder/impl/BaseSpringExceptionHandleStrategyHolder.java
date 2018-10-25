package com.wangcheng.zeus.common.web.exception.holder.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.wangcheng.zeus.common.web.exception.MissAnnotationException;
import com.wangcheng.zeus.common.web.exception.holder.ExceptionHandleStrategyHolder;
import com.wangcheng.zeus.common.web.exception.strategy.BaseExceptionHandlerStrategyAware;
import com.wangcheng.zeus.common.web.exception.strategy.ExceptionHandleStrategy;
import com.wangcheng.zeus.common.web.exception.strategy.annotation.ExceptionHandleType;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * @author  Administrator
 * @Date: 2018/9/26 21:12
 * @Description: 提供策略支持
 */
public class BaseSpringExceptionHandleStrategyHolder  implements ExceptionHandleStrategyHolder,ApplicationContextAware {

    private  final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 默认的异常处理策略对象 在spring容器中的名称
     */
    private static final String DEFAULT_EXCEPTION_HANDLE_STRATEGY = "defaultExceptionHandleStrategy";

    /**
     * 保存异常以及对应的策略信息
     */
    private Map<Class<? extends Throwable>,ExceptionHandleStrategy> strategyMap;

    /**
     * 默认的异常处理策略
     */
    private ExceptionHandleStrategy defaultExceptionHandleStrategy;

    @Override
    public ExceptionHandleStrategy getStrategy(@NotNull Throwable e) {
        ExceptionHandleStrategy strategy = strategyMap.get(e.getClass());
        return Objects.isNull(strategy) ? defaultExceptionHandleStrategy : strategy;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        logger.info("开始加载异常处理策略");
        Map<String, ExceptionHandleStrategy> strategyBeans = applicationContext.getBeansOfType(ExceptionHandleStrategy.class);
        Assert.state(CollectionUtils.isEmpty(strategyMap),"没有加载到异常处理策略对象");
        initData(strategyBeans);
        logger.info("加载到异常处理策略:[{}]",printStrategyMap());
    }

    /**
     * 初始化数据
     */
    private void initData(Map<String, ExceptionHandleStrategy> strategyBeans){
        Set<Class<? extends Throwable>> throwableTypes = Sets.newHashSet();
        Set<ExceptionHandleStrategy> strategies = Sets.newHashSet();
        loadThrowableAndStrategy(throwableTypes,strategies,strategyBeans);
        strategyMap = Maps.newHashMapWithExpectedSize(throwableTypes.size());
        initStrategyMap(throwableTypes,strategies);
    }

    /**
     * 加载异常和策略
     * @param throwableTypes 异常类型
     * @param strategies     策略对象
     */
    private void loadThrowableAndStrategy(Set<Class<? extends Throwable>> throwableTypes,Set<ExceptionHandleStrategy> strategies,@NotEmpty Map<String, ExceptionHandleStrategy> strategyBeans){
        strategyBeans.forEach((strategyName,strategy)->{
            ExceptionHandleType annotation = strategy.getClass().getAnnotation(ExceptionHandleType.class);
            if(Objects.isNull(annotation)){
                throw new MissAnnotationException("异常处理策略类必须有ExceptionHandleType注解");
            }
            Class<? extends Throwable>[] classes = annotation.value();
            throwableTypes.addAll(Arrays.asList(classes));
            strategies.add(strategy);
            if(DEFAULT_EXCEPTION_HANDLE_STRATEGY.equals(strategyName)){
                defaultExceptionHandleStrategy = strategy;
            }
        });
    }

    /**
     * 初始化 StrategyMap
     * @param throwableTypes
     * @param strategies
     */
    private void initStrategyMap(@NotEmpty  Set<Class<? extends Throwable>> throwableTypes,@NotEmpty Set<ExceptionHandleStrategy> strategies) {
        throwableTypes.forEach((throwable)->{
            ExceptionHandleStrategy strategy = selectMatchedStrategy(throwable, strategies);
            strategyMap.put(throwable,strategy);
        });
    }

    /**
     *  从策略集合中选择一个匹配当前异常的策略
     *   匹配的原则是：优先选择用户提供的策略,在匹配基础策略，
     *                 如果没有找到合适的策略，匹配默认的处理策略
     * @param throwable   要匹配的异常类型
     * @param strategies  策略集合
     * @return 匹配上的策略
     */
    private ExceptionHandleStrategy selectMatchedStrategy(@NotNull  Class<? extends Throwable> throwable,@NotEmpty Set<ExceptionHandleStrategy> strategies) {

        List<ExceptionHandleStrategy> tempList = Lists.newLinkedList();
        strategies.forEach(strategy -> {
            ExceptionHandleType handleType = strategy.getClass().getAnnotation(ExceptionHandleType.class);
            Class<? extends Throwable>[] targetTypes = handleType.value();
            long count = Arrays.stream(targetTypes).filter(throwable::equals).count();
            if(count > 0){
                tempList.add(strategy);
            }
        });
        if(CollectionUtils.isEmpty(tempList)){
            return defaultExceptionHandleStrategy;
        }
        for (ExceptionHandleStrategy strategy : tempList) {
            if(strategies instanceof BaseExceptionHandlerStrategyAware){
                return strategy;
            }
        }
        return tempList.get(0);
    }

    /**
     * 打印策略map
     * @return
     */
    public String printStrategyMap(){
        StringBuffer stringBuffer = new StringBuffer();
        strategyMap.forEach((key,value)->{
            stringBuffer.append("\n{");
            stringBuffer.append(key.toString());
            stringBuffer.append(":");
            stringBuffer.append(value);
            stringBuffer.append("}\n");
        });
        return stringBuffer.toString();
    }
}
