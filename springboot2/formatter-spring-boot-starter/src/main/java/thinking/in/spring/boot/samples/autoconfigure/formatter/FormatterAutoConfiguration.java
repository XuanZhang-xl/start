package thinking.in.spring.boot.samples.autoconfigure.formatter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWebApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * created by XUAN on 2019/12/24
 */
@Configuration
// matchIfMissing = true 配置不存在时, 视为匹配
@ConditionalOnProperty(prefix = "formatter", name = "enabled", havingValue = "true", matchIfMissing = true)
// 非web应用有效
@ConditionalOnNotWebApplication
public class FormatterAutoConfiguration {

    /**
     * 构建{@link DefaultFormatter}
     * @return
     */
    @Bean
    @ConditionalOnMissingClass(value = "com.alibaba.fastjson.JSON")
    public Formatter defaultFormatter() {
        return new DefaultFormatter();
    }

    /**
     * JSON格式 {@link JsonFormatter}
     * @return
     */
    @Bean
    @ConditionalOnClass(name = "com.fasterxml.jackson.databind.ObjectMapper")
    @ConditionalOnMissingBean(name = "com.fasterxml.jackson.databind.ObjectMapper")
    public Formatter jsonFormatter() {
        return new JsonFormatter();
    }

    @Bean
    @ConditionalOnBean(ObjectMapper.class)
    public Formatter objectMapperFormatter(ObjectMapper objectMapper) {
        return new JsonFormatter(objectMapper);
    }

    @Bean
    @ConditionalOnClass(name = "com.alibaba.fastjson.JSON")
    public Formatter fastJsonFormatter() {
        return new FastJsonFormatter();
    }
}
