package com.wangcheng.zeus.core.config.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

/**
 * @author: evan
 * @date: 2018/11/6 20:07
 * @description:
 */
public class TokenUtils {


    private static final String TOKEN_PREFIX = "Bearer";

    private static final String TOKEN_KEY = "accessToken";

    private static final SignatureAlgorithm DECODE_TOKEN_ALGORITHM = SignatureAlgorithm.HS256;

    /**
     * 基于jwt 生成一个token
     * @param claims    要打包的数据
     * @param secret    加密秘钥
     * @param expiration 过期时间
     * @return
     */
    public static String generateJwtToken(Map<String,Object> claims,String secret,Long expiration){
        Assert.notNull(claims,"claims is  null");
        Iterator<String> keys = claims.keySet().iterator();
        while (keys.hasNext()){
            String key = keys.next();
            if(Objects.isNull(claims.get(key))){
                claims.remove(key);
            }
        }
      return   Jwts.builder().setClaims(claims).setExpiration(new Date(System.currentTimeMillis()+expiration)).signWith(DECODE_TOKEN_ALGORITHM,secret).compact();
    }

    /**
     * 通过 Jwts.parse  来解析获取Claims
     * @param token   要解析的token
     * @param secret  加密的秘钥
     * @return
     */
    public static Claims parseClaims(String token,String secret){
        if(!StringUtils.hasText(token)){
            throw new MalformedJwtException("非法请求");
        }
        return  Jwts.parser().setSigningKey(secret).parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody();
    }

    /**
     * 通过ObjectMapper的方式反序列化 从token中解析出Claims
     *
     * @param token
     * @return
     */
    public static Claims readClaims(String token){
        Assert.hasText(token,"token 不能为空");
        java.lang.String[] split = token.split("\\.");
        Assert.isTrue(split.length == 3,"非法请求");
        String payload = split[1];
        String body = new String(Base64.getDecoder().decode(payload));
        try {
            return JsonUtils.readValue(body,DefaultClaims.class);
        } catch (IOException e) {
            return new DefaultClaims();
        }
    }

    /**
     * 从request 中获取token
     * @param request
     * @return
     */
    public static String getToken(HttpServletRequest request){
        Assert.notNull(request,"request 不能为空");
        String token = request.getHeader(TOKEN_KEY);
        if(StringUtils.isEmpty(token)){
            token = request.getParameter(TOKEN_KEY);
        }
        return token;
    }
}
