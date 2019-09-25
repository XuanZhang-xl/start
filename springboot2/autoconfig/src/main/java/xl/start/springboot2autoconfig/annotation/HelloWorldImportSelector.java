package xl.start.springboot2autoconfig.annotation;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import xl.start.springboot2autoconfig.configuration.HelloWorldConfiguration;

/**
 * HelloWorld {@link ImportSelector} 的实现
 *
 * @author XUAN
 * @since 2019/09/22
 */
public class HelloWorldImportSelector implements ImportSelector {


    /**
     * 这里传入的  AnnotationMetadata  是 class xl.start.springboot2.bootstrap.EnableHelloWorldBootstrap 的包装类
     * <p>
     * 也就是说这方法的意思是返回启动类所需要的所有bean的class的名字?
     *
     * @param importingClassMetadata
     * @return
     */
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{HelloWorldConfiguration.class.getName()};
    }
}
