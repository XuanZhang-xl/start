package xl.start.springboot2autoconfig.configuration;

import org.springframework.context.annotation.Configuration;
import xl.start.springboot2autoconfig.annotation.EnableHelloWorld;
import xl.start.springboot2autoconfig.condition.ConditionalOnSystemProperty;

/**
 * HelloWorld 自动装配
 *
 * @author XUAN
 * @since 2019/09/21
 */
@Configuration     // 模式注解装配
@EnableHelloWorld  //Spring @Enable 模块装配
@ConditionalOnSystemProperty(name = "user.name", value = "MSI-PC")  // 条件装配
public class HelloWorldAutoConfiguration {
}
