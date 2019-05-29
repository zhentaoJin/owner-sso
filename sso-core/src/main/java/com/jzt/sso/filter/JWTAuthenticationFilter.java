package com.jzt.sso.filter;

import com.alibaba.fastjson.JSON;
import com.jzt.sso.jwt.JwtTokenUtils;
import com.jzt.sso.service.UserService;
import com.jzt.sso.utils.CookieUtils;
import com.jzt.sso.utils.R;
import com.jzt.sso.utils.SpringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * token的校验
 * 该类继承自BasicAuthenticationFilter，在doFilterInternal方法中，
 * 从http头的Authorization 项读取token数据，然后用Jwts包提供的方法校验token的合法性。
 * 如果校验通过，就认为这是一个取得授权的合法请求
 */
public class JWTAuthenticationFilter extends BasicAuthenticationFilter {

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager){
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        //获取medical_data验证用户是否登录，以及是否用户票据过期
        String header = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
        //前端在用户登录后设置cookie，用于其他子系统认证时候检验用户是否登录过
        Cookie cookie= CookieUtils.get(request,"token");

        if((header == null || !header.startsWith(JwtTokenUtils.TOKEN_PREFIX))&&cookie==null){
            chain.doFilter(request,response);
            return;
        }

        UsernamePasswordAuthenticationToken authenticationToken = null;
           if(header!=null){
               // 如果请求头中有token，则进行解析，并且设置认证信息
               try {
                   authenticationToken = getAuthentication(header);
               } catch (Exception e) {
                   e.printStackTrace();
                   AuthenticationFail(response);
               }
           }
           else{
               try {
                   authenticationToken = getAuthentication(cookie.getValue());
               } catch (Exception e) {
                   e.printStackTrace();
                   AuthenticationFail(response);
               }
           }

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request,response);
    }

    /**
     *这里从token中获取用户信息并新建一个token
     */
    private UsernamePasswordAuthenticationToken getAuthentication(String header) {

        String token = header.replace(JwtTokenUtils.TOKEN_PREFIX, "");
        String principal = JwtTokenUtils.getUsername(token);
        if (principal != null) {
            UserService userService = (UserService) SpringUtils.getBean("userService");
            String credentials=userService.selectByName(principal).getPassword();
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            return new UsernamePasswordAuthenticationToken(principal, credentials, authorities);
        }
        return null;
    }

//    private UsernamePasswordAuthenticationToken getAuthenticationByCookle(String phone){
//        if (phone != null) {
//            UserService userService = (UserService) SpringUtils.getBean("userService");
//            String credentials=userService.selectByName(phone).getPassword();
//            List<GrantedAuthority> authorities = new ArrayList<>();
//            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//            return new UsernamePasswordAuthenticationToken(phone, credentials, authorities);
//        }
//        return null;
//    }

    private void AuthenticationFail(HttpServletResponse response) throws IOException {
        String contentType = "application/json; charset=utf-8";
        response.setContentType(contentType);
        PrintWriter out = response.getWriter();
        out.print(JSON.toJSON(R.error(401,"token过期了，请重新登录认证。")));
        out.flush();
        out.close();
        return;
    }

}
