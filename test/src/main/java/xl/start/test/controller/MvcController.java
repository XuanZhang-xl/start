package xl.starttest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xl.start.test.entity.User;
import xl.start.test.vo.ResponseVo;

import java.util.concurrent.Callable;

/**
 * created by XUAN on 2019/05/18
 */
@Controller("/mvcController")
public class MvcController {

    private static final Logger logger = LoggerFactory.getLogger(MvcController.class);

    /**
     * 测试表单提交
     * @return
     */
    @RequestMapping("/form")
    public ResponseVo formCommit(User user) {
        logger.info("接受到参数: {}", user);
        return new ResponseVo();
    }

    @PostMapping("/asyn")
    @ResponseBody
    public Callable<String> processUpload() {
        return new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(3000);
                return "true";
            }
        };

    }
}
