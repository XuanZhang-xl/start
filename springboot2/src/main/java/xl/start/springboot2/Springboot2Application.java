package xl.start.springboot2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @SpringBootApplication 的子注解: @EnableAutoConfiguration 自动装配, 具体配置在 META-INF下的 spring.factories配置文件中, 解析: TODO
 */
@SpringBootApplication
// 扫描目录下所有servlet, 如果不写 basePackages, 目测就是当前目录
//@ServletComponentScan
public class Springboot2Application {

    public static void main(String[] args) {
        SpringApplication.run(Springboot2Application.class, args);
    }

}
