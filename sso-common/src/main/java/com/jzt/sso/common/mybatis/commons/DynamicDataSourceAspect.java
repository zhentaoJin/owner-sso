package com.jzt.sso.common.mybatis.commons;

import com.jzt.sso.common.mybatis.annotation.TargetDataSource;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author RocLiu [apedad@qq.com]
 * @version 1.0
 */
@Aspect
@Component public class DynamicDataSourceAspect {
    private static final Logger logger = Logger.getLogger(DynamicDataSourceAspect.class);

    @Pointcut("target(com.jzt.sso.common.mybatis.CrudIntf)") public void pointCut() {

    }

    /**
     * 执行方法前更换数据源
     *
     * @param joinPoint        切点
     * @param targetDataSource 动态数据源
     */
    @Before("@annotation(targetDataSource)") public void doBefore(JoinPoint joinPoint,
        TargetDataSource targetDataSource) {
        DataSourceKey dataSourceKey = targetDataSource.dataSourceKey();
        if (dataSourceKey == DataSourceKey.DB_OTS) {
            logger.info(String.format("设置数据源为  %s", DataSourceKey.DB_OTS));
            DynamicDataSourceContextHolder.set(DataSourceKey.DB_OTS);
        } else {
            //todo 多数据源在此切换
            logger.info(String.format("使用默认数据源  %s", DataSourceKey.DB_OTS));
            DynamicDataSourceContextHolder.set(DataSourceKey.DB_OTS);
        }
    }

    /**
     * 执行方法后清除数据源设置
     *
     * @param joinPoint        切点
     * @param targetDataSource 动态数据源
     */
    @After("@annotation(targetDataSource)") public void doAfter(JoinPoint joinPoint,
        TargetDataSource targetDataSource) {

        logger.info(String.format("当前数据源  %s  执行清理方法", targetDataSource.dataSourceKey()));
        DynamicDataSourceContextHolder.clear();
    }

    @Before(value = "pointCut()") public void before(JoinPoint joinPoint) {
        // 获取切入的 Method
        MethodSignature joinPointObject = (MethodSignature)joinPoint.getSignature();
        Method method = joinPointObject.getMethod();
        TargetDataSource targetDataSource;
        // 如果方法上没有注解，则搜索类上是否有注解
        if (method.isAnnotationPresent(TargetDataSource.class)) {
            targetDataSource = method.getAnnotation(TargetDataSource.class);
        } else {
            targetDataSource = AnnotationUtils.findAnnotation(joinPoint.getThis().getClass(), TargetDataSource.class);
        }

        if (targetDataSource != null) {
            DynamicDataSourceContextHolder.set(targetDataSource.dataSourceKey());
        } else {
            DynamicDataSourceContextHolder.set(DataSourceKey.DB_DEFAULT);
        }

    }

    @After(value = "pointCut()") public void after(JoinPoint joinPoint) {
        DynamicDataSourceContextHolder.clear();
    }
}
