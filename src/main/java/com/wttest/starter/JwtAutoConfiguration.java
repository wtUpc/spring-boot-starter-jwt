package com.wttest.starter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 〈功能概述〉<br>
 *
 * @className: JwtAutoConfiguration
 * @package: com.wttest.starter
 * @author: wangtong
 * @date: 2021/11/29 1:54 pm
 **/

@Configuration
@ConditionalOnClass(JwtService.class)
@EnableConfigurationProperties(JwtServiceProperties.class)
public class JwtAutoConfiguration {

    @Autowired
    private JwtServiceProperties jwtServiceProperties;

    @Bean
    JwtService jwtService(){
        return new JwtService(jwtServiceProperties.getBase64Security(),jwtServiceProperties.getIssuer());
    }

    @Bean
    JwtUtils jwtUtils(){
        return new JwtUtils();
    }

//    //创建bean 的一些判断条件注解
//    @Bean
//    @ConditionalOnMissingBean
//    @ConditionalOnProperty(prefix = "jwt", value = "enabled", havingValue = "true")
//    JwtUtils jwtUtils() {
//        return new JwtUtils();
//    }
}


