package xl.start.dubboprovider.config;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xl.start.dubboapi.service.GreetingsService;
import xl.start.dubboprovider.service.GreetingsServiceImpl;

/**
 * created by XUAN on 2019/9/2
 */
@Configuration
public class DubboProviderConfiguration {

    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("zookeeper://140.143.206.160:2181");
        return registryConfig;
    }

    //@Bean
    //public ServiceConfig serviceConfig() {
    //    ServiceConfig service = new ServiceConfig();
    //    service.setApplication(new ApplicationConfig("dubbo-provider"));
    //    service.setRegistry(new RegistryConfig("zookeeper://140.143.206.160:2181"));
    //    service.setInterface(GreetingsService.class);
    //    service.setRef(new GreetingsServiceImpl());
    //    service.export();
    //    return service;
    //}
}
