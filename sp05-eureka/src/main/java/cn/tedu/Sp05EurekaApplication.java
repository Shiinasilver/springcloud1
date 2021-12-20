package cn.tedu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer // 触发 eureka server 的自动配置
@SpringBootApplication
public class Sp05EurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(Sp05EurekaApplication.class, args);
    }

}