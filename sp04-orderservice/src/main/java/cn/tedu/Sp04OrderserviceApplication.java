package cn.tedu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class Sp04OrderserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(Sp04OrderserviceApplication.class, args);
    }

}
