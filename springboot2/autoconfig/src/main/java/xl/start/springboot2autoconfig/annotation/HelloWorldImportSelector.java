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
     * 这里传入的  AnnotationMetadata  是 xl.start.springboot2autoconfig.configuration.HelloWorldAutoConfiguration 类的元信息包装类
     *
     * 因为 @EnableHelloWorld 标注在HelloWorldAutoConfiguration类上, 而@EnableHelloWorld导入了这个ImportSelector
     *
     * @param importingClassMetadata
     * @return
     */
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{HelloWorldConfiguration.class.getName()};
    }
}
