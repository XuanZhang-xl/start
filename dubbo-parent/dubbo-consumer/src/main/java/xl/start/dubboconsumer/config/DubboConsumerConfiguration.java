package xl.start.dubboconsumer.config;

import org.apache.dubbo.config.RegistryConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * created by XUAN on 2019/9/2
 */
@Configuration
public class DubboConsumerConfiguration {

    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("zookeeper://140.143.206.160:2181");
        return registryConfig;
    }
}
