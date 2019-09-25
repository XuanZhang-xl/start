package xl.start.springboot2autoconfig.bootstrap;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import xl.start.springboot2autoconfig.repository.MyFirstLevelRepository;

/**
 * Repository引导类
 *
 * @author XUAN
 * @since 2019/09/21
 */
// 扫描. 获得xl.start.springboot2.repository包下所有的bean
@ComponentScan(basePackages = "xl.start.springboot2.repository")
public class RepositoryBoostrap {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(RepositoryBoostrap.class)
                // 非web类型
                .web(WebApplicationType.NONE)
                .run(args);
        MyFirstLevelRepository repository = context.getBean("myFirstLevelRepository", MyFirstLevelRepository.class);
        System.out.println(repository == null ? "myFirstLevelRepository不存在" : "myFirstLevelRepository存在");


        // 关闭上下文
        context.close();
    }
}
