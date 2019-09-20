package xl.start.test.websocket;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * springboot方式写 WebSocketEndPoint
 * created by XUAN on 2019/9/20
 */
public class SpringbootWebSocketEndPoint implements WebSocketHandler {

    private static AtomicInteger onlineCount = new AtomicInteger(0);

    private static final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<String, WebSocketSession>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("新客户端连接:");
        System.out.println("sessionId:" + session.getId());
        System.out.println("acceptedProtocol:" + session.getAcceptedProtocol());
        System.out.println("uri:" + session.getUri().toString());
        System.out.println("localAddress.HostName:" + session.getLocalAddress().getHostName());
        System.out.println("localAddress.HostAddress:" + session.getLocalAddress().getAddress().getHostAddress());
        System.out.println("localAddress.Port:" + session.getLocalAddress().getPort());
        System.out.println("Attributes:" + JSONObject.toJSONString(session.getAttributes()));
        sessions.put(session.getId(), session);
        int onlineNum = addOnlineCount();
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        System.out.println("收到客户端发来的消息: " + message);
        // echo
        session.sendMessage(new TextMessage(message.getPayload().toString()));
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        System.out.println("sessionId: " + session.getId() + "发生错误:");
        sessions.remove(session);
        int onlineNum = subOnlineCount();
        exception.printStackTrace();
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        System.out.println("sessionId: " + session.getId() + "已关闭连接");
        sessions.remove(session);
        int onlineNum = subOnlineCount();
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }


    public static int getOnlineCount() {
        return onlineCount.get();
    }

    private int addOnlineCount() {
        return onlineCount.incrementAndGet();
    }

    private int subOnlineCount() {
        return onlineCount.decrementAndGet();
    }
}
