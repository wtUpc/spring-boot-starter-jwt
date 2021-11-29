package com.wttest.starter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.Map;

/**
 * 〈功能概述〉<br>
 *
 * @className: JwtUtils
 * @package: com.wttest.starter
 * @author: wangtong
 * @date: 2021/11/29 1:53 pm
 **/

public class JwtUtils {


    /**
     * @Author wangtong
     * @Description 加密方法
     * @Date 2:24 pm 2021/11/29
     * @Param
     * @return
     **/
    public Claims parseJWT(String jsonWebToken, String base64Security) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(base64Security))
                    .parseClaimsJws(jsonWebToken).getBody();
            return claims;
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * @Author wangtong
     * @Description 解密方法
     * @Date 2:25 pm 2021/11/29
     * @Param [map, audience, issuer, TTLMillis, base64Security]
     * @return java.lang.String
     **/
    public String createJWT(Map map,
                            String audience, String issuer, long TTLMillis, String base64Security) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        //生成签名密钥 就是一个base64加密后的字符串？
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64Security);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        //添加构成JWT的参数
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
                .setIssuedAt(now)  //创建时间
                .setSubject(map.toString()) //主题，也差不多是个人的一些信息
                .setIssuer(issuer) //发送谁
                .setAudience(audience) //个人签名
                .signWith(signatureAlgorithm, signingKey);  //估计是第三段密钥
        //添加Token过期时间
        if (TTLMillis >= 0) {
            // 过期时间
            long expMillis = nowMillis + TTLMillis;
            // 现在是什么时间
            Date exp = new Date(expMillis);
            // 系统时间之前的token都是不可以被承认的
            builder.setExpiration(exp).setNotBefore(now);
        }
        //生成JWT
        return builder.compact();
    }
}
