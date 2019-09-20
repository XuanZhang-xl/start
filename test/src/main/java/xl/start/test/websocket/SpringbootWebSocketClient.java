package xl.start.test.websocket;

import com.alibaba.fastjson.JSONObject;
import org.springframework.lang.Nullable;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 *
 * websocket client
 * created by XUAN on 2019/9/20
 */
public class SpringbootWebSocketClient implements WebSocketHandler {


    private static final String WS_URI = "ws://localhost:8082/springbootwebsocket";

    private static WebSocketSession webSocketSession;

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws IOException, InterruptedException {
        StandardWebSocketClient client = new StandardWebSocketClient();
        WebSocketHttpHeaders headers = new WebSocketHttpHeaders();
        headers.add("headers", "head");
        ListenableFuture<WebSocketSession> future = client.doHandshake(new SpringbootWebSocketClient(), headers, URI.create(WS_URI));
        future.addCallback(new ListenableFutureCallback<WebSocketSession>() {
            @Override
            public void onSuccess(@Nullable WebSocketSession result) {
                webSocketSession = result;
                countDownLatch.countDown();
                System.out.println("Successfully connected");
            }
            @Override
            public void onFailure(Throwable ex) {
                ex.printStackTrace();
            }
        });

        countDownLatch.await();
        if (webSocketSession != null) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String input = br.readLine();
            while(!input.equals("exit")) {
                webSocketSession.sendMessage(new TextMessage(input));
                input = br.readLine();
            }
            webSocketSession.close();
        } else {
            System.out.println("webSocketSession为空");
        }

    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("与服务端连接:");
        System.out.println("sessionId:" + session.getId());
        System.out.println("acceptedProtocol:" + session.getAcceptedProtocol());
        System.out.println("localAddress.HostName:" + session.getLocalAddress().getHostName());
        System.out.println("localAddress.HostAddress:" + session.getLocalAddress().getAddress().getHostAddress());
        System.out.println("localAddress.Port:" + session.getLocalAddress().getPort());
        System.out.println("Attributes:" + JSONObject.toJSONString(session.getAttributes()));
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        System.out.println("收到服务端消息:" + message.getPayload().toString());
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        System.out.println("sessionId: " + session.getId() + "发生错误:");
        exception.printStackTrace();
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        System.out.println("sessionId: " + session.getId() + "已关闭连接");
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
