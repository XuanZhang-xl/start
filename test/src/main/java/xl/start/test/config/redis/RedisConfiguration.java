package xl.start.test.config.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import xl.start.test.listener.RedisSubscriber;

import javax.annotation.Resource;

/**
 * created by XUAN on 2019/8/6
 */
@Configuration
public class RedisConfiguration {

    @Resource
    private LettuceConnectionFactory lettuceConnectionFactory;

    @Resource
    private RedisSubscriber redisSubscriber;

    @Bean
    public RedisTemplate<String, String> redisTemplate() {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(lettuceConnectionFactory);
        return template;
    }

    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(redisSubscriber, new PatternTopic("codehole.*"));
        container.addMessageListener(redisSubscriber, new PatternTopic("testsubpub"));//配置要订阅的订阅项
        return container;
    }
}
