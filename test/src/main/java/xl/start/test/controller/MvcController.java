package xl.start.test.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import xl.start.test.entity.User;
import xl.start.test.vo.ResponseVo;

import java.util.concurrent.Callable;

/**
 * created by XUAN on 2019/05/18
 */
@RestController("mvcController")
@RequestMapping("/mvc")
public class MvcController {

    private static final Logger logger = LoggerFactory.getLogger(MvcController.class);

    /**
     * 测试表单提交
     * @return
     */
    @PostMapping("/form")
    public ResponseVo formCommit(User user) {
        logger.info("接受到参数: {}", user);
        return new ResponseVo();
    }

    /**
     * 测试时候通畅
     * @return
     */
    @GetMapping("/submit")
    public ResponseVo submit(String data) {
        logger.info("接受到data: {}", data);
        return ResponseVo.buildSuccess(data);
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
