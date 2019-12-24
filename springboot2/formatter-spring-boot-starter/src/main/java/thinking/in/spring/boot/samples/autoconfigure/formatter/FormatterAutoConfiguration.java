package thinking.in.spring.boot.samples.autoconfigure.formatter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * created by XUAN on 2019/12/24
 */
@Configuration
public class FormatterAutoConfiguration {

    /**
     * 构建{@link DefaultFormatter}
     * @return
     */
    @Bean
    @ConditionalOnMissingClass(value = "com.fasterxml.jackson.databind.ObjectMapper")
    public Formatter defaultFormatter() {
        return new DefaultFormatter();
    }

    /**
     * JSON格式 {@link JsonFormatter}
     * @return
     */
    @Bean
    @ConditionalOnClass(name = "com.fasterxml.jackson.databind.ObjectMapper")
    public Formatter jsonFormatter() {
        return new JsonFormatter();
    }


}
