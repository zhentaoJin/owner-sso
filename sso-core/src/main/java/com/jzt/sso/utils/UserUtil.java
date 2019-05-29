package com.jzt.sso.utils;

import com.jzt.sso.model.UserEntity;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtil {


    /**
     *
     * 获取spring Security 当前登录的用户信息
     */
    public static UserEntity getUser(){
        return  (UserEntity) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
    }

    /**
     *
     * 获取spring Security 当前登录的用户的账号名
     */
    public static String getUserName(){
        Object object=SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        return  object.toString();

    }

}
