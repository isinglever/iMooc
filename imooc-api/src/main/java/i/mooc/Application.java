package i.mooc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication

//扫描 mybatis 通用 mapper 所在包
@MapperScan(basePackages = "i.mooc.mapper")
@ComponentScan(basePackages = {"i.mooc", "org.n3r.idworker"})
@EnableScheduling
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
