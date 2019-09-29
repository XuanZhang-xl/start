package xl.start.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xl.start.test.vo.ResponseVo;
import xl.start.test.vo.ws.Greeting;
import xl.start.test.websocket.WebSocketEndPoint;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * created by XUAN on 2019/09/19
 */
@RestController
@RequestMapping("/wspush")
public class WebSocketController {


    @Autowired
    @Qualifier("ws")
    private ThreadPoolTaskExecutor executor;

    /**
     * 所有的ws客户端引用
     */
    Map<String, WebSocketEndPoint> clients = WebSocketEndPoint.clients;

    /**
     * 将消息发送给所有连接到 WebSocketEndPoint 的用户
     * @param message
     * @return
     * @throws IOException
     */
    @RequestMapping("/to/all")
    public ResponseVo pushMessageToAll(String message) throws IOException {
        if (StringUtils.isEmpty(message)) {
            return ResponseVo.buildFail("message不可为空");
        }
        if (!clients.isEmpty()) {
            WebSocketEndPoint.sendMessageAll(message);
        }
        return ResponseVo.buildSuccess();
    }

    /**
     * 将消息发送给指定的连接到 WebSocketEndPoint 的用户
     * @param username
     * @param message
     * @return
     * @throws IOException
     */
    @RequestMapping("/send/to/{username}")
    public ResponseVo send(@PathVariable("username") String username, String message) throws IOException {
        if (StringUtils.isEmpty(message)) {
            return ResponseVo.buildFail("message不可为空");
        }
        WebSocketEndPoint webSocketEndPoint = clients.get(username);
        if (webSocketEndPoint == null) {
            return ResponseVo.buildFail("username不可为空或用户未登陆");
        }
        webSocketEndPoint.sendMessageToSelf(message);
        return ResponseVo.buildSuccess();
    }

    /**
     * 将随机消息发送给指定的连接到 WebSocketEndPoint 的用户
     * @param username
     * @param message
     * @return
     * @throws IOException
     */
    @RequestMapping("/send/random/to/{username}")
    public ResponseVo sendRandomTo(@PathVariable("username") String username, String message) {
        WebSocketEndPoint webSocketEndPoint = clients.get(username);
        if (webSocketEndPoint == null) {
            return ResponseVo.buildFail("username不可为空或用户未登陆");
        }
        Random random = new Random();
        // 异步推送
        executor.execute(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    int nextInt = random.nextInt(2000);
                    webSocketEndPoint.sendMessageToSelf(StringUtils.isEmpty(message) ? String.valueOf(i) : message + i);
                    Thread.sleep(nextInt);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return ResponseVo.buildSuccess();
    }

    /**
     * 获得所有当前连接
     * @return
     * @throws IOException
     */
    @RequestMapping("/get/all")
    public ResponseVo getAll() throws IOException {
        return ResponseVo.buildSuccess(clients.keySet());
    }
}
