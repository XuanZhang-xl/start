package xl.start.springboot2.bootstrap;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import xl.start.springboot2.annotation.EnableHelloWorld;
import xl.start.springboot2.repository.MyFirstLevelRepository;

/**
 * EnableHelloWorld的引导类
 * {@link xl.start.springboot2.annotation.EnableHelloWorld}
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
