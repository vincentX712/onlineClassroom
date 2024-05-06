package com.officerschool.onlineclassroom.starter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author : create by anyuxin
 * @version : v1.0
 * @date : 4/15/24
 */
@ComponentScan(basePackages = {"com.officerschool*"})
@SpringBootApplication
@MapperScan("com.officerschool.onlineclassroom.dao.mapper")
public class OnlineClassroomStarterApplication {
    public static void main(String[] args) {
        SpringApplication.run(OnlineClassroomStarterApplication.class, args);
        System.out.println("======================================SUCCESS======================================");
    }
}
