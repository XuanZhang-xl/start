package xl.start.springboot2.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;

/**
 * 系统属性条件判断
 * @author XUAN
 * @since 2019/09/22
 */
public class OnSystemPropertyCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {

        // 以键值对方式获得 ConditionalOnSystemProperty 的所有属性
        Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(ConditionalOnSystemProperty.class.getName());

        // 获得Bootstrap引导类上属性的值
        String propertyName = String.valueOf(annotationAttributes.get("name"));
        String propertyValue = String.valueOf(annotationAttributes.get("value"));

        // 获得系统属性值 MSI-PC
        String javaPropertyValue = System.getProperty(propertyName);

        return propertyValue.equals(javaPropertyValue);
    }
}
