package xl.start.dubboprovider.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by XUAN on 2019/9/2
 */
@RestController
@RequestMapping("/hello")
public class DubboProviderController {

    @RequestMapping("/{dubbo}")
    public String hello(@PathVariable(value = "dubbo") String youName) {
        return "hello, dubbo provider!";
    }
}
