package xl.start.springboot2autoconfig.bootstrap;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import xl.start.springboot2autoconfig.annotation.EnableHelloWorld;

/**
 * EnableHelloWorld的引导类
 * {@link EnableHelloWorld}
 *
 * @author XUAN
 * @since 2019/09/21
 */
@EnableHelloWorld
public class EnableHelloWorldBootstrap {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(EnableHelloWorldBootstrap.class)
                // 非web类型
                .web(WebApplicationType.NONE)
                .run(args);
        String helloWorld = context.getBean("helloWorld", String.class);
        System.out.println(helloWorld == null ? "helloWorld不存在" : "helloWorld存在");

        // 关闭上下文
        context.close();
    }

}
