package xl.start.dubboprovider.service;

import org.apache.dubbo.config.annotation.Service;
import xl.start.dubboapi.service.GreetingsService;

/**
 * created by XUAN on 2019/9/2
 */
@Service()
public class GreetingsServiceImpl implements GreetingsService {
    @Override
    public String sayHello(String name) {
        return "hello " + name + "!";
    }
}
