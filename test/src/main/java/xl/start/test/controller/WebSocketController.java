package xl.start.test.controller;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xl.start.test.websocket.WebSocketEndPoint;
import xl.start.test.vo.ResponseVo;

import java.io.IOException;
import java.util.Map;

/**
 * created by XUAN on 2019/09/19
 */
@RestController
@RequestMapping("/wspush")
public class WebSocketController {

    // 所有的ws客户端引用
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
    public ResponseVo pushMessageToAll(@PathVariable("username") String username, String message) throws IOException {
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
}
