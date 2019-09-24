package xl.start.springboot2.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xl.start.springboot2.annotation.EnableHelloWorld;
import xl.start.springboot2.condition.ConditionalOnSystemProperty;

/**
 * HelloWorld 自动装配
 * @author XUAN
 * @since 2019/09/21
 */
@Configuration     // 模式注解装配
@EnableHelloWorld  //Spring @Enable 模块装配
@ConditionalOnSystemProperty(name = "user.name", value = "MSI-PC")  // 条件装配
public class HelloWorldAutoConfiguration {
}
