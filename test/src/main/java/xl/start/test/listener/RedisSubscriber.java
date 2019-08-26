package xl.start.test.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * redis订阅者/消费者
 * created by XUAN on 2019/8/26
 */
@Component("redisSubscriber")
public class RedisSubscriber implements MessageListener {

    @Resource
    StringRedisTemplate redisTemplate;

    @Override
    public void onMessage(Message message, byte[] bytes) {
        System.out.println("收到redis的消息:  " + message);
        byte[] body = message.getBody();
        byte[] channel = message.getChannel();
        String msg = redisTemplate.getStringSerializer().deserialize(body);
        String topic = redisTemplate.getStringSerializer().deserialize(channel);
        System.out.println("监听到topic为" + topic + "的消息：" + msg);
    }
}
