package com.itlike;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author : zhousy
 * @version : 1.0
 * @date : 2022/7/13 17:31
 */

@SpringBootApplication
@MapperScan("com.itlike.mapper")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
