package com.jzt.sso.annotation;

import java.lang.annotation.*;

/**
 * @Description 系统日志注解
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {
    String value() default "";
    String moduleName() default "";
}
