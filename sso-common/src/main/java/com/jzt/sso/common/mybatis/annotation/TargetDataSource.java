package com.jzt.sso.common.mybatis.annotation;

import com.jzt.sso.common.mybatis.commons.DataSourceKey;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author RocLiu [apedad@qq.com]
 * @version 1.0
 */
@Component
@Target({ElementType.TYPE, ElementType.METHOD}) @Retention(RetentionPolicy.RUNTIME)
public @interface TargetDataSource {
    DataSourceKey dataSourceKey() default DataSourceKey.DB_DEFAULT;
}
