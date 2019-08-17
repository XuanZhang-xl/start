package xl.start.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xl.start.test.vo.ResponseVo;


/**
 * created by XUAN on 2019/8/6
 */
@RestController("redisClusterController")
@RequestMapping("/redis")
public class RedisClusterController {

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @GetMapping("/get")
    public ResponseVo<String> get(String key) {
        return ResponseVo.buildSuccess(redisTemplate.opsForValue().get(key));
    }

    @GetMapping("/set")
    public ResponseVo<String> set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
        return ResponseVo.buildSuccess();
    }

    @GetMapping("/list/push")
    public ResponseVo<String> listPush(String key, String value) {
        redisTemplate.opsForList().leftPush(key, value);
        return ResponseVo.buildSuccess();
    }

    @GetMapping("/list/pop")
    public ResponseVo<String> listPop(String key) {
        return ResponseVo.buildSuccess(redisTemplate.opsForList().rightPop(key));
    }

    @GetMapping("/hash/put")
    public ResponseVo<String> hashPut(String key, String hashKey, String value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
        return ResponseVo.buildSuccess();
    }

    @GetMapping("/hash/get")
    public ResponseVo<String> hashGet(String key, String hashKey) {
        return ResponseVo.buildSuccess(redisTemplate.opsForHash().get(key, hashKey));
    }
}
