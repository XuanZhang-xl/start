package xl.start.springboot2autoconfig.bootstrap;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import xl.start.springboot2autoconfig.service.CalculateService;

/**
 * @author XUAN
 * @since 2019/09/22
 */
@SpringBootApplication(scanBasePackages = "xl.start.springboot2autoconfig.service")
public class CalculateServiceBootstrap {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(CalculateServiceBootstrap.class)
                // 非web类型
                .web(WebApplicationType.NONE)
                .profiles("Java8")
                .run(args);
        CalculateService calculateService = context.getBean(CalculateService.class);
        System.out.println("求和结果: " + calculateService.sum(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        // 关闭上下文
        context.close();
    }
}
