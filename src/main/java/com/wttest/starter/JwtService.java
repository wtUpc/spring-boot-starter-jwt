package com.wttest.starter;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * 〈功能概述〉<br>
 *
 * @className: JwtService
 * @package: com.wttest.starter
 * @author: wangtong
 * @date: 2021/11/29 1:53 pm
 **/

public class JwtService {

    @Autowired
    JwtUtils jwtUtils;

    private String base64Security;

    private String issuer;

    public JwtService(String base64Security, String issuer) {
        this.base64Security = base64Security;
        this.issuer = issuer;
    }

    /**
     * @Author wangtong
     * @Description 创建 map中放个人信息，可以被他们获取到，第二个是发送给谁，第三参数是过期时间
     * @Date 2:27 pm 2021/11/29
     * @Param
     * @return
     **/
    public String createPersonToken(Map map, String audience, long TTLMillis) {
        String personToken = jwtUtils.createJWT(map, audience, this.issuer, TTLMillis, this.base64Security);
        return personToken;
    }

    /**
     * @Author wangtong
     * @Description token
     * @Date 2:28 pm 2021/11/29
     * @Param
     * @return
     **/
    public Claims parsePersonJWT(String personToken) {
        Claims claims = jwtUtils.parseJWT(personToken, this.base64Security);
        return claims;
    }
}
