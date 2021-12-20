package cn.tedu.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class Sp06Application {

    public static void main(String[] args) {
        SpringApplication.run(Sp06Application.class, args);
    }

}
