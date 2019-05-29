package com.jzt.sso.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyLogoutHandler implements LogoutHandler {
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        try {
            String logout = request.getParameter("logout");//logout即为前端传来自定义跳转url地址
            response.sendRedirect(logout);//实现自定义重定向
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
