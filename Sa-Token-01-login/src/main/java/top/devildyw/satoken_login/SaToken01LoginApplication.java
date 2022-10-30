package top.devildyw.satoken_login;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages="top.devildyw")
public class SaToken01LoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(SaToken01LoginApplication.class, args);
    }

}
