package xl.start.springboot2autoconfig.condition;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * java系统属性条件判断
 *
 * @author XUAN
 * @since 2019/09/22
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@Conditional(OnSystemPropertyCondition.class)
public @interface ConditionalOnSystemProperty {

    /**
     * java系统属性名称
     *
     * @return
     */
    String name();

    /**
     * java系统属性值
     *
     * @return
     */
    String value();
}
