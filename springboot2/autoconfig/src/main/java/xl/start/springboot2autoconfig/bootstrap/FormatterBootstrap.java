package xl.start.springboot2autoconfig.bootstrap;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import thinking.in.spring.boot.samples.autoconfigure.formatter.Formatter;

import java.util.HashMap;
import java.util.Map;

/**
 * 根据依赖模块不同而实例化不同实现类的测试
 *
 * 如果formatter-spring-boot-starter 依赖
 *
 *         <dependency>
 *             <groupId>com.alibaba</groupId>
 *             <artifactId>fastjson</artifactId>
 *             <version>1.2.41</version>
 *             <optional>true</optional>
 *         </dependency>
 *
 * 则注入DefaultFormatter
 *
 * 如果依赖
 *
 *         <dependency>
 *             <groupId>com.alibaba</groupId>
 *             <artifactId>fastjson</artifactId>
 *             <version>1.2.41</version>
 *         </dependency>
 * 则注入FastJsonFormatter
 *
 * created by XUAN on 2019/12/24
 */
@EnableAutoConfiguration
public class FormatterBootstrap {

    public static void main(String[] args){
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(FormatterBootstrap.class)
                //.web(WebApplicationType.NONE)
                .web(WebApplicationType.SERVLET)
                .properties("formatter.enabled=true")
                .run(args);
        Map<String, Object> data = new HashMap<>();
        data.put("name", "xl");
        Map<String, Formatter> beans = applicationContext.getBeansOfType(Formatter.class);
        if (beans.isEmpty()) {
            throw new NoSuchBeanDefinitionException(Formatter.class);
        }
        beans.forEach((beanName, formatter) -> {
            System.out.printf("[Bean Name : %s] %s.format(data) : %s\n", beanName, formatter.getClass().getSimpleName(), formatter.format(data));
        });
        applicationContext.close();
    }

}
