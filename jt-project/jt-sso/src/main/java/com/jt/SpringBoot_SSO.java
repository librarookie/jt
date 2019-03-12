package com.jt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author chao
 * @date 2019/2/20 - 14:16
 */
@SpringBootApplication
@MapperScan("com.jt.sso.mapper")
public class SpringBoot_SSO {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot_SSO.class, args);
    }
}
