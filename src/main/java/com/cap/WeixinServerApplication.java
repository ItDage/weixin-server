
package com.cap;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@SpringBootConfiguration
@MapperScan(basePackages={"com.cap.dao"})
public class WeixinServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeixinServerApplication.class, args);
    }
}
