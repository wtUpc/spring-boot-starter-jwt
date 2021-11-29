package com.wttest.starter;



//需要在配置文件配置所需要的属性
//本例的配置文件
//jwt.base64Security = 0914234854!wa
//jwt.issuer = alienlabManage

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 〈功能概述〉<br>
 *
 * @ className: JwtServiceProperties
 * @ package: com.wttest.starter
 * @ author: wangtong
 * @ date: 2021/11/29 1:54 pm
 **/
@ConfigurationProperties(prefix = "jwt")
public class JwtServiceProperties {

    /**
     * 密钥
     * 系统的简称   [名称要与配置文件相同]
     */
    private String base64Security;

    private String issuer;

    public String getBase64Security() {
        return base64Security;
    }

    public void setBase64Security(String base64Security) {
        this.base64Security = base64Security;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }
}
