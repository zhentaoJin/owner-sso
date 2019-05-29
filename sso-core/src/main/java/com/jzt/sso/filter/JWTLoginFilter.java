package com.jzt.sso.filter;


import com.alibaba.fastjson.JSON;
import com.jzt.sso.dto.JwtUser;
import com.jzt.sso.exception.CustomException;
import com.jzt.sso.jwt.JwtTokenUtils;
import com.jzt.sso.security.UrlCache;
import com.jzt.sso.service.UserService;
import com.jzt.sso.utils.R;
import com.jzt.sso.utils.SpringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 验证用户名密码正确后，生成一个token，并将token返回给客户端
 * 该类继承自UsernamePasswordAuthenticationFilter，重写了其中的2个方法
 * attemptAuthentication ：接收并解析用户凭证。
 * successfulAuthentication ：用户成功登录后，这个方法会被调用，我们在这个方法里生成token。
 */
public class JWTLoginFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    private RequestCache requestCache = new HttpSessionRequestCache();

    private String targetUrlParameter = null;

    private boolean alwaysUseDefaultTargetUrl = false;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    public JWTLoginFilter(AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;

    }

    /**
     *接收并解析用户凭证
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        //从输入流中获取登录信息
        try{
            System.out.println("尝试登录");
            //手机号
            String phone = request.getParameter("username");

            //用户openId
            String openId = request.getParameter("password");

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            phone,openId,new ArrayList<>()
                    )
            );
        }catch (CustomException e){

            throw new CustomException(R.error("用户密码不正确，请检查输入密码。"));
        }
    }

    /**
     * 用户成功登录后，这个方法会被调用，我们在这个方法里生成token
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        //利用缓存做客户端认证后跳转回原来界面
        String targetUrl= UrlCache.url;
        System.out.println("重定向地址:!!!!!!!!!"+targetUrl);
        if (targetUrl == null||targetUrl=="") {

        }
        else {
            UrlCache.url="";
        }
        myAuthentication(request,response,authResult,targetUrl);


    }

    /**
     * 这是验证失败时候调用的方法
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(R.error("用户名或密码不正确，请检查。")));
    }

    protected String getTargetUrlParameter() {
        return this.targetUrlParameter;
    }

    protected boolean isAlwaysUseDefaultTargetUrl() {
        return this.alwaysUseDefaultTargetUrl;
    }


    protected final void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute("SPRING_SECURITY_LAST_EXCEPTION");
        }
    }

    protected RedirectStrategy getRedirectStrategy() {
        return this.redirectStrategy;
    }

    private void myAuthentication(HttpServletRequest request,
                                  HttpServletResponse response,
                                  Authentication authResult,String url) throws IOException {
        String principal = ((JwtUser)authResult.getPrincipal()).getUsername();

        String token = JwtTokenUtils.createToken(principal,false);
        UserService userService = (UserService) SpringUtils.getBean("userService");
        Long userId= userService.selectByName(principal).getId();
        System.out.println("【登录成功，token->】"+JwtTokenUtils.TOKEN_PREFIX+token);
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        String resultToken= JwtTokenUtils.TOKEN_PREFIX+token;
        response.getWriter().write(JSON.toJSONString(R.ok().put("token",resultToken).put("username",principal).put("userId",userId).put("targetUrl",url)));
    }
}
