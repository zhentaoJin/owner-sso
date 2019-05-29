package com.jzt.sso.jwt;

import io.jsonwebtoken.*;

import java.util.Date;

public class JwtTokenUtils {

    public static final String TOKEN_HEADER = "test_data";

    public static  final String  TOKEN_HEADER_BACK = "medical_data_back";

    public static final String TOKEN_PREFIX = "owner-token_";

    private static final String SECRET = "jwtsecretdemo";

    /**
     * 签发者
     */
    private static final String ISS = "GZCR";

    /**
     * 过期时间是3600秒，既是1个小时
     */
    private static final long EXPIRATION = 7200L;

    /**
     * 选择了记住我之后的过期时间为7天
     */
    private static final long EXPIRATION_REMEMBER = 604800L;

    /**
     * 创建token
     * @param username
     * @param isRememberMe
     * @return
     */
    public static String createToken(String username, boolean isRememberMe) {
        long expiration = isRememberMe ? EXPIRATION_REMEMBER : EXPIRATION;
        String token = Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .setIssuer(ISS)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .compact();
        return token;
    }

    /**
     * 从token中获取用户名
     * @param token
     * @return
     */
    public static String getUsername(String token){
        Claims claims= null;
        claims = getTokenBody(token);
        return claims.getSubject();
    }

    /**
     * 判断是否已过期
     * @param token
     * @return
     */
    public static boolean isExpiration(String token){
        boolean result=getTokenBody(token).getExpiration().before(new Date());
        return result;
    }

    public static Claims getTokenBody(String token){
        Jws<Claims> result= Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token);
        return  result.getBody();
    }
}
