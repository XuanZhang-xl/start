package xl.start.springboot2autoconfig.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解驱动
 *
 * @author XUAN
 * @since 2019/09/21
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
// 方式一, 无弹性, 导入Configuration中的bean
// @Import(HelloWorldConfiguration.class)
// 方式二, 自定义导入多个Configuration中的bean
@Import(HelloWorldImportSelector.class)
public @interface EnableHelloWorld {
}
