package com.jzt.sso.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jzt.sso.utils.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class OwnerFailureHandler extends SimpleUrlAuthenticationFailureHandler {
//    @Override
//    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
//        httpServletResponse.setCharacterEncoding("utf-8");
//        httpServletResponse.setContentType("text/html;charset=utf-8");
//        httpServletResponse.getWriter().write(JSON.toJSONString(R.error("authentication failed, reason: " + e.getMessage())));
//    }


    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SecurityProperties securityProperties;

    // AuthenticationException 认证过程中产生的异常
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        logger.info("MyAuthenticationSuccessHandler login failure!");
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setContentType("application/json;charset=UTF-8");
//          response.getWriter().write(objectMapper.writeValueAsString(exception));
            response.getWriter().write(objectMapper.writeValueAsString(R.error(exception.getMessage())));
    }
}
