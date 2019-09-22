package xl.start.springboot2.bootstrap;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import xl.start.springboot2.condition.ConditionalOnSystemProperty;
import xl.start.springboot2.service.CalculateService;

/**
 * 系统属性条件引导类
 * @author XUAN
 * @since 2019/09/22
 */
//@ConditionalOnSystemProperty(name = "user.name", value = "xuan")
public class ConditionalOnSystemPropertyBootstrap {

    @Bean
    // 条件装配, 当系统属性  user.name==MSI-PC 时会注册bean , 具体实现在 OnSystemPropertyCondition
    @ConditionalOnSystemProperty(name = "user.name", value = "MSI-PC")
    public String helloWorld() {
        return "Hello, World, MSI-PC!";
    }


    public static void main(String[] args) {
        // 当 ConditionalOnSystemPropertyBootstrap 在这里传入时, 自动成为一个bean, 所以不声明它为bean也没事
        ConfigurableApplicationContext context = new SpringApplicationBuilder(ConditionalOnSystemPropertyBootstrap.class)
                // 非web类型
                .web(WebApplicationType.NONE)
                .run(args);

        String helloWorld = context.getBean("helloWorld", String.class);
        System.out.println("helloWorld bean: " + helloWorld);
        context.close();
    }
}
