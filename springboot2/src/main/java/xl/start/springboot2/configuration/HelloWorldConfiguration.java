package xl.start.springboot2.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author XUAN
 * @since 2019/09/21
 */
@Configuration
public class HelloWorldConfiguration {

    /**
     * 🐮比了, String类型的bean也可以
     * 如果不指定名称, 方法名即bean的名称, 也就是这个bean的name属性是helloWorld
     * @return
     */
    @Bean
    public String helloWorld() {
        return "Hello, World!";
    }
}
