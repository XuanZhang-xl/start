package xl.start.springboot2autoconfig.config;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import xl.start.springboot2autoconfig.configuration.webmvc.SpringWebMvcConfiguration;

import javax.servlet.ServletContext;

/**
 * 如果要以servlet启动, 则需要注释掉这个类, 因为整个项目只能有一个 {@link WebApplicationInitializer} 的实现类, 否则报错
 *
 * @see ContextLoader#initWebApplicationContext(ServletContext)
 * created by XUAN on 2019/12/23
 */
public class SpringWebMvcServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return of(SpringWebMvcConfiguration.class);
    }

    @Override
    protected String[] getServletMappings() {
        return of("/*");
    }

    /**
     * ...转为数组的简略方法
     * @param values
     * @param <T>
     * @return
     */
    private <T> T[] of(T... values) {
        return values;
    }
}
